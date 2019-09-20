package com.app.sos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAdapter extends RecyclerView.Adapter<billing_view_holder> {
    JSONArray jsarr;
    Activity a;



    public MyAdapter()
    {
        this.jsarr = jsarr;

        this.a = a;
    }
    @NonNull
    @Override
    public billing_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        billing_view_holder view =new billing_view_holder(LayoutInflater.from(a).inflate(R.layout.activity_billing,parent,false));

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull billing_view_holder holder, int position) {
        try {
            // iterating for each json object in json array
            final JSONObject job = (JSONObject) jsarr.get(position);
            // binding values from json object to cell layout via view holder
            holder.date_id.setText(job.getString("date"));
            holder.bill_id.setText(job.getString("bill"));
            holder.amount_id.setText(job.getString("amount"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
