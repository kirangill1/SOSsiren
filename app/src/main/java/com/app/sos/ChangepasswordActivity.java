package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangepasswordActivity extends AppCompatActivity {
    EditText password,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        EditText password = (EditText) findViewById(R.id.edit1);
        EditText confirmpassword = (EditText) findViewById(R.id.edit2);
    }
    public void change_pass(View v)
    {
        final String pass = password.getText().toString();

        String confirm_pass = confirmpassword.getText().toString();


        if (pass.equals("")) {
            Toast.makeText( ChangepasswordActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirm_pass.equals("")) {
            Toast.makeText(ChangepasswordActivity.this, "please confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(confirm_pass)) {
            Toast.makeText(ChangepasswordActivity.this, "password and confirm password does not match", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject job = new JSONObject();

        try {
            job.put("customers_email", getIntent().getStringExtra("email"));
            job.put("customers_password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://testing.reitindia.org/welcome/insert_customers", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("done")) {

                                // code to save value through out app

                                Toast.makeText(ChangepasswordActivity.this, "password updated", Toast.LENGTH_SHORT).show();

                                finish();


                            } else
                            {
                                Toast.makeText(ChangepasswordActivity.this, "password and email does not match", Toast.LENGTH_SHORT).show();
                                return;
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

        AppController app = new AppController(ChangepasswordActivity.this);
        app.addToRequestQueue(jobjreq);
    }
}

