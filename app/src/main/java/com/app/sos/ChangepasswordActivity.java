package com.app.sos;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChangepasswordActivity extends AppCompatActivity {
    EditText passwordBox,confirmpasswordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        passwordBox = (EditText) findViewById(R.id.edit1);
        confirmpasswordBox = (EditText) findViewById(R.id.edit2);
    }
}
