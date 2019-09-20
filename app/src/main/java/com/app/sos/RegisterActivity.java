package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    EditText name_et, mobile_et, address_et, user_et, appid_et, pass_et, confirm_et;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name_et     = (EditText) findViewById(R.id.name);
        mobile_et   = (EditText) findViewById(R.id.mobile);
        address_et  = (EditText) findViewById(R.id.address);
        user_et     = (EditText) findViewById(R.id.email);
        appid_et    = (EditText) findViewById(R.id.id);
        pass_et     = (EditText) findViewById(R.id.pass);
        confirm_et  = (EditText) findViewById(R.id.confirmpass);
        button      = (Button) findViewById(R.id.submit);

    }



    public void submit(View view) {

        String name = name_et.getText().toString();
        String mobile = mobile_et.getText().toString();
        String address = address_et.getText().toString();
        String email = user_et.getText().toString();
        String appid = appid_et.getText().toString();
        String password = pass_et.getText().toString();
        String confirmpass = confirm_et.getText().toString();
        if (name.equals("")) {
            Toast.makeText(RegisterActivity.this, "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mobile.length() < 10) {
            Toast.makeText(RegisterActivity.this, "re-enter the mobile ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(RegisterActivity.this, "enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 8) {
            Toast.makeText(RegisterActivity.this, "enter password atleast eight digit", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmpass.equals("")) {
            Toast.makeText(RegisterActivity.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (address.equals("")) {
            Toast.makeText(RegisterActivity.this, "enter the address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!confirmpass.equals(password)) {
            Toast.makeText(RegisterActivity.this, "password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();
        try {
            job.put("customers_name", name);
            job.put("customers_mobile", mobile);
            job.put("customers_email", email);
            job.put("customers_password", password);
            job.put("customers_confirmpass", confirmpass);
            job.put("customers_appid", appid);
            job.put("customers_address", address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://testing.reitindia.org/welcome/insert_customers", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    if (response.getString("key").equals("0")) {
                        Toast.makeText(RegisterActivity.this, "check your email", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.getString("key").equals("1")) {
                        Toast.makeText(RegisterActivity.this, "done", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(RegisterActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                {
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);

                    }
                });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(2000, 2, 2));

        AppController app = new AppController(RegisterActivity.this);
        app.addToRequestQueue(jobjreq);

    }
}
