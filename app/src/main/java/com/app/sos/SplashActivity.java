package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;

import static android.accounts.AccountManager.KEY_PASSWORD;

public class SplashActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String storedUsername = prefs.getString("customers_email", "Default Value if not found");
        String storedPassword = prefs.getString("customers_password", ""); //return nothing if no pass saved
        if (!storedUsername .equalsIgnoreCase("") || !storedPassword .equalsIgnoreCase("")) {
            intent  = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }else {
            intent  = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 3000);

    }

}// class


