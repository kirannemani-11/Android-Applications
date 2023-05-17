package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HrviewHolder extends RecyclerView.ViewHolder {
    TextView day;
    TextView hour;
    ImageView icon;
    TextView temperature;
    TextView descp;
    HrviewHolder(View view)
    {
        super(view);
        day = view.findViewById(R.id.hourday);
        hour = view.findViewById(R.id.hourtime);
        icon = view.findViewById(R.id.houricon);
        temperature = view.findViewById(R.id.hourtemp);
        descp = view.findViewById(R.id.hourdesc);
    }
}
