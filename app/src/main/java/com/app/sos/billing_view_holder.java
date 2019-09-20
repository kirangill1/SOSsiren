package com.app.sos;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.sos.R;

public class billing_view_holder extends RecyclerView.ViewHolder {
  public TextView date_id,bill_id,amount_id;
  public billing_view_holder(View itemView) {
    super(itemView);
    date_id=(TextView)itemView.findViewById(R.id.date_id);
    bill_id=(TextView)itemView.findViewById(R.id.bill_id);
    amount_id = (TextView) itemView.findViewById(R.id.amount_id);

  }
}