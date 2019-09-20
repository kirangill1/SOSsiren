package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateprofileActivity extends AppCompatActivity {
    EditText name_et, email_et, address_et, mobile_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        name_et = (EditText) findViewById(R.id.name);
        email_et = (EditText) findViewById(R.id.email);
        address_et = (EditText) findViewById(R.id.address);
        mobile_et = (EditText) findViewById(R.id.mobile);
        get_values();
    }
    public void get_values() {
        JSONObject jobj = new JSONObject();

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);

        String email = sp.getString("customers_email", "");


        try {
            jobj.put("email_key", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://testing.reitindia.org/welcome/insert_customers", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr = response.getJSONArray("result");

                    JSONObject job_box = (JSONObject) jarr.get(0);
                    email_et.setText(job_box.getString("customers_email"));
                    name_et.setText(job_box.getString("customers_name"));
                    address_et.setText(job_box.getString("customers_address"));
                    mobile_et.setText(job_box.getString("customers_mobile"));


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


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));
        AppController app = new AppController(UpdateprofileActivity.this);
        app.addToRequestQueue(jobreq);
    }


    public void update(View v) {
        String name = name_et.getText().toString();
        String mobile = mobile_et.getText().toString();
        String address = address_et.getText().toString();


        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        String email = sp.getString("email", "");



        JSONObject job = new JSONObject();

        try {
            job.put("customers_email",email);
            job.put("customers_mobile",mobile);
            job.put("customers_address", address);
            job.put("customers_name", name);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://testing.reitindia.org/welcome/insert_customers", job, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    JSONArray jarr = response.getJSONArray("result");

                    JSONObject job_box = (JSONObject) jarr.get(0);
                    email_et.setText(job_box.getString("email"));
                    name_et.setText(job_box.getString("name"));
                    mobile_et.setText(job_box.getString("mobile"));
                    address_et.setText(job_box.getString("address"));


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


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));
        AppController app = new AppController(UpdateprofileActivity.this);
        app.addToRequestQueue(jobreq);
    }
}
