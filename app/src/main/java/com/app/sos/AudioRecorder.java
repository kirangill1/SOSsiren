package com.app.sos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class AudioRecorder extends AppCompatActivity {
    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;
    static final String AB = "abcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();



    private MediaRecorder recorder = null;


    private MediaPlayer player = null;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;

    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) finish();

    }


    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }

    private void startRecording() {

        recorder = new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        recorder.setOutputFile(fileName);

        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording() {

        recorder.stop();
        recorder.release();
        recorder = null;
    }


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_audio_recorder);
        hasSDCard();
        // Record to the external cache directory for visibility
        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();

        File audioVoice = new File(fileName+ File.separator + "voices");
        if(!audioVoice.exists()){
            audioVoice.mkdir();
        }
        fileName = fileName + File.separator + "voices/" + generateVoiceFilename(6) + ".3gpp";
        System.out.println("Audio path : " + fileName);

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);


    }

    private String generateVoiceFilename( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    private void hasSDCard(){
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(isSDPresent)        {
            System.out.println("There is SDCard");
        }
        else{
            System.out.println("There is no SDCard");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }
    }

    public void start_record(View view) {

        startRecording();

    }

    public void stop_record(View view) {

        stopRecording();
    }

    public void start_play(View view) {

        startPlaying();
    }

    public void stop_play(View view) {

        stopPlaying();
    }

    // call this function start recording snippet
    public void recordSnippet() {

        startRecording();


        final Handler handler = new Handler();
        final int delay = 15000; //milliseconds

        handler.postDelayed(new Runnable() {
            public void run() {
                //do something

                stopRecording();

                // make this function to save recording files to server

                //saveRecording();

                startRecording();

                handler.postDelayed(this, delay);
            }
        }, delay);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            recordSnippet();
        }
        return false;
    }

}

