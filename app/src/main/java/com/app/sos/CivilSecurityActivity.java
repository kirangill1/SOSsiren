package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

public class CivilSecurityActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;

    private String user_location = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_security);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }

    }


    public void open_map(View view)
    {

        Intent i = new Intent(CivilSecurityActivity.this , MapsActivity.class);

        startActivity(i);

    }


    private void sendSMS(String phoneNumber, String message)
    {

        SmsManager sms = SmsManager.getDefault();

        sms.sendTextMessage(phoneNumber, null, message, null , null);


    }

    public void sos(View view) {


        SharedPreferences sp = getSharedPreferences("persons" , MODE_PRIVATE);

        String name1 = sp.getString("name1" , "");
        String name2 = sp.getString("name2" , "");
        String name3 = sp.getString("name3" , "");


        String contact1 = sp.getString("contact1" , "");
        String contact2 = sp.getString("contact2" , "");
        String contact3 = sp.getString("contact3" , "");

        if(contact1.trim().length()==10)
        {
            sendSMS(contact1.trim() , "Hello "+ name1 +" username is in emergency at location "+user_location+" <sos-alert-123>");
        }

        if(contact2.trim().length()==10)
        {
            sendSMS(contact2.trim() , "Hello "+ name2 +" username is in emergency at location "+user_location+" <sos-alert-123>");
        }

        if(contact3.trim().length()==10)
        {
            sendSMS(contact3.trim() , "Hello "+ name3 +" username is in emergency at location "+user_location+" <sos-alert-123>");
        }

        finish();

        Toast.makeText(CivilSecurityActivity.this , "Alert message sent" , Toast.LENGTH_SHORT).show();

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


                             user_location =  getAddress(_latitude , _longitude);

                        }
                    }
                });
    }

    private String getAddress( double latitude , double longitude )
    {

        Geocoder geocoder = new Geocoder(CivilSecurityActivity.this);

        try {
            List<Address> addressList = geocoder.getFromLocation(latitude , longitude , 1);
            if (addressList != null && addressList.size() > 0) {
                String locality = addressList.get(0).getAddressLine(0);
                String country = addressList.get(0).getCountryName();
                if (country != null) {
                    //resutText.setText(locality + "  " + country);


                    return locality+" "+country;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }
}
