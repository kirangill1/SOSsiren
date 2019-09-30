package com.app.sos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.tasks.OnSuccessListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class CivilSecurityActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;

    private String user_location = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_security);
        // Record to the external cache directory for visibility


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }

    }


    public void open_map(View view) {

        Intent i = new Intent(CivilSecurityActivity.this, MapsActivity.class);

        startActivity(i);

    }



    private void sendSMS(String phoneNumber, String message) {

        SmsManager sms = SmsManager.getDefault();

        sms.sendTextMessage(phoneNumber, null, message, null, null);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {

            //Do something
            SharedPreferences sp = getSharedPreferences("persons", MODE_PRIVATE);

            String name1 = sp.getString("name1", "");
            String name2 = sp.getString("name2", "");
            String name3 = sp.getString("name3", "");


            String contact1 = sp.getString("contact1", "");
            String contact2 = sp.getString("contact2", "");
            String contact3 = sp.getString("contact3", "");

            if (contact1.trim().length() == 10) {
                sendSMS(contact1.trim(), "Hello " + name1 + " username is in emergency at location " + user_location + " <sos-alert-123>");
            }

            if (contact2.trim().length() == 10) {
                sendSMS(contact2.trim(), "Hello " + name2 + " username is in emergency at location " + user_location + " <sos-alert-123>");
            }

            if (contact3.trim().length() == 10) {
                sendSMS(contact3.trim(), "Hello " + name3 + " username is in emergency at location " + user_location + " <sos-alert-123>");
            }

            finish();

            Toast.makeText(CivilSecurityActivity.this, "Alert message sent", Toast.LENGTH_SHORT).show();

        }


        return true;
    }


    public void sos(View view) {


        SharedPreferences sp = getSharedPreferences("persons", MODE_PRIVATE);

        String name1 = sp.getString("name1", "");
        String name2 = sp.getString("name2", "");
        String name3 = sp.getString("name3", "");


        String contact1 = sp.getString("contact1", "");
        String contact2 = sp.getString("contact2", "");
        String contact3 = sp.getString("contact3", "");

        if (contact1.trim().length() == 10) {
            sendSMS(contact1.trim(), "Hello " + name1 + " username is in emergency at location " + user_location + " <sos-alert-123>");
        }

        if (contact2.trim().length() == 10) {
            sendSMS(contact2.trim(), "Hello " + name2 + " username is in emergency at location " + user_location + " <sos-alert-123>");
        }

        if (contact3.trim().length() == 10) {
            sendSMS(contact3.trim(), "Hello " + name3 + " username is in emergency at location " + user_location + " <sos-alert-123>");
        }

        finish();

        Toast.makeText(CivilSecurityActivity.this, "Alert message sent", Toast.LENGTH_SHORT).show();

    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, you have to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object

                            double _latitude = location.getLatitude();
                            double _longitude = location.getLongitude();


                            user_location = getAddress(_latitude, _longitude);


                            JSONObject job = new JSONObject();
                            Calendar c = Calendar.getInstance();

                            int hours = c.get(Calendar.HOUR_OF_DAY);
                            int minute = c.get(Calendar.MINUTE);
                            int seconds = c.get(Calendar.SECOND);

                            int day = c.get(Calendar.DAY_OF_MONTH);
                            int month = c.get(Calendar.MONTH) + 1;
                            int year = c.get(Calendar.YEAR);


                            String time = String.valueOf(hours) + "-" + String.valueOf(minute) + "-" + String.valueOf(seconds);
                            String date = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

                            System.out.println(time + " " + String.valueOf(_latitude) + "/" + String.valueOf(_longitude));

                            try {
                                job.put("lat_key", String.valueOf(_latitude));
                                job.put("lng_key", String.valueOf(_longitude));
                                job.put("time_key", time);
                                job.put("dte_key", date);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            JsonObjectRequest jobjreq = new JsonObjectRequest("http://xx.php", job,
                                    new com.android.volley.Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                        }

                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    System.out.println(error);

                                }
                            });

                            jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

                            AppController app = new AppController(CivilSecurityActivity.this);

                            app.addToRequestQueue(jobjreq);


                        }

                    }
                });
    }

    private String getAddress(double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(CivilSecurityActivity.this);

        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                String locality = addressList.get(0).getAddressLine(0);
                String country = addressList.get(0).getCountryName();
                if (country != null) {
                    //resultText.setText(locality + "  " + country);


                    return locality + " " + country;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "user_location";

    }


    public void recording(View view) {
        Intent i = new Intent(CivilSecurityActivity.this, AudioRecorder.class);
        startActivity(i);
        finish();
    }

    public void add_person(View view) {
        Intent i = new Intent(CivilSecurityActivity.this, AddpersonActivity.class);

        startActivity(i);

    }
}