package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void update_profile(View view) {
        Intent intent = new Intent(SettingActivity.this, UpdateprofileActivity.class);
        startActivity(intent);
        finish();
    }

    public void change_password(View view) {
        Intent intent = new Intent(SettingActivity.this, ChangepasswordActivity.class);
        startActivity(intent);
        finish();
    }

    public void share_app(View view) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the app via play store now...";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void about_us(View view) {
        Intent intent = new Intent(SettingActivity.this, AboutusActivity.class);
        startActivity(intent);
        finish();
    }

    public void rating(View view) {
        final String appPackageName = "com.app.sos";

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
