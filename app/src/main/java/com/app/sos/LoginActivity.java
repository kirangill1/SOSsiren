package com.app.sos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText user_et, pass_et;
   Button loginBox;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_et = (EditText) findViewById(R.id.edittext_user);
        pass_et = (EditText) findViewById(R.id.edittext_pass);
        loginBox = (Button) findViewById(R.id.login);
    }




    public void signin(View view) {

        final String Email = user_et.getText().toString();

        String Password = pass_et.getText().toString();

        if (Email.equals("")) {
            Toast.makeText(LoginActivity.this, "please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Password.equals("")) {
            Toast.makeText(LoginActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        try {
            job.put("customers_email", Email);
            job.put("customers_password", Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://testing.reitindia.org/welcome/login", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("1")) {
                                Toast.makeText(LoginActivity.this, "logged in", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();


                            } else {
                                Toast.makeText(LoginActivity.this, "please check your email and password again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(LoginActivity.this);
        app.addToRequestQueue(jobjreq);
    }



    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void forget(View v) {
        Intent i = new Intent(LoginActivity.this, ForgetpassActivity.class);
        startActivity(i);

    }
}