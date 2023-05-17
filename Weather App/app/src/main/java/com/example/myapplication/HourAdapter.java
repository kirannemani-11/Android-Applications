package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HourAdapter extends RecyclerView.Adapter<HrviewHolder>{
    private final ArrayList<Hour> hours;
    private final MainActivity ma;
    HourAdapter(ArrayList<Hour> hours, MainActivity ma)
    {
        this.hours = hours;
        this.ma = ma;
    }
    @NonNull
    @Override
    public HrviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hour_view, parent, false);
        return new HrviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HrviewHolder holder, int position) {
        Hour hour = hours.get(position);
        Date dateTime = new Date(hour.getDatetimeEpoch() * 1000); // Java time values need milliseconds
        SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm a", Locale.getDefault());
        SimpleDateFormat dayDate = new SimpleDateFormat("EEEE", Locale.getDefault());

        holder.day.setText(DateUtils.isToday(dateTime.getTime()) ? "Today" : dayDate.format(dateTime));
        holder.hour.setText(timeOnly.format(dateTime));
        if(hour.getTemp()<16){holder.temperature.setText(hour.getTemp() + "°C");}
        else {
        holder.temperature.setText(hour.getTemp() + "°F"); }
        String icon = hour.getIcon();
        //Log.d(TAG, icon);
        icon = icon.replace("-","_");
        //Log.d(TAG, icon);
        holder.icon.setImageResource(ma.getIcon(icon));
        holder.descp.setText(hour.getConditions());
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }
}
