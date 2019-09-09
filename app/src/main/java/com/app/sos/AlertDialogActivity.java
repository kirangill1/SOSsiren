package com.app.sos;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class AlertDialogActivity extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        String title = i.getStringExtra("title");

        final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.siren);
        mPlayer.start();


        final AlertDialog alertDialog = new AlertDialog.Builder(this , R.style.MyAlertDialogStyle).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage("Need help !!!!!!!!!!");
        alertDialog.setIcon(R.drawable.civil);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                mPlayer.stop();

            }
        });

        alertDialog.show();



    }
}
