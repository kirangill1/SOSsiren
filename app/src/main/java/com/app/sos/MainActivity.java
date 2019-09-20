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



        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    public void open_contact_us(View view) {

        Intent i = new Intent(MainActivity.this  , ContactUsActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void open_setting(View view) {

        Intent i = new Intent(MainActivity.this  , SettingActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void open_billing(View view) {

        Intent i = new Intent(MainActivity.this  , BillingActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }
    public void log_out (View v){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
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

}
