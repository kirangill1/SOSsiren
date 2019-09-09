package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;

    private int PERMISSION_ALL = 1;

    public static Activity main_activity;

    private EditText contact1 , contact2 , contact3;

    private EditText name1 , name2 , name3;

    private final String  SHARED_PREF_NAME = "persons";

    String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            android.Manifest.permission.READ_SMS,
            Manifest.permission.SYSTEM_ALERT_WINDOW

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_activity = MainActivity.this;

        drawerLayout = findViewById(R.id.drawer_activity);

        contact1 = findViewById(R.id.contact);
        contact2 = findViewById(R.id.contact2);
        contact3 = findViewById(R.id.contact3);

        name1 = findViewById(R.id.name);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);

        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME , MODE_PRIVATE);

        name1.setText(sp.getString("name1" , ""));
        name2.setText(sp.getString("name2" , ""));
        name3.setText(sp.getString("name3" , ""));

        contact1.setText(sp.getString("contact1" , ""));
        contact2.setText(sp.getString("contact2" , ""));
        contact3.setText(sp.getString("contact3" , ""));

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    public void open_contact_us(View view) {

        Intent i = new Intent(MainActivity.this  , ContactUsActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void open_support(View view) {

        Intent i = new Intent(MainActivity.this  , SupportActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void open_billing(View view) {

        Intent i = new Intent(MainActivity.this  , BillingActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void open_financial_security(View view) {

        Intent i = new Intent(MainActivity.this  , FinancialSecurityActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void open_civil_security(View view) {

        Intent i = new Intent(MainActivity.this  , CivilSecurityActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void open_personal_security(View view) {

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void open_drawer(View view) {

        drawerLayout.openDrawer(GravityCompat.START);
    }


    // function to check if permissions are granted

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void save_persons(View view) {

        String name_str = name1.getText().toString();
        String name2_str = name2.getText().toString();
        String name3_str = name3.getText().toString();

        String contact_str = contact1.getText().toString();
        String contact2_str = contact2.getText().toString();
        String contact3_str = contact3.getText().toString();

        SharedPreferences.Editor sp = getSharedPreferences(SHARED_PREF_NAME , MODE_PRIVATE).edit();

        sp.putString("name1" , name_str);
        sp.putString("name2" , name2_str);
        sp.putString("name3" , name3_str);

        sp.putString("contact1" , contact_str);
        sp.putString("contact2" , contact2_str);
        sp.putString("contact3" , contact3_str);

        sp.commit();

        Toast.makeText(MainActivity.this , "Person contact saved" , Toast.LENGTH_SHORT).show();

    }
}
