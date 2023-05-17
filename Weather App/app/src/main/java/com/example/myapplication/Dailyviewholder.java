package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Dailyviewholder extends RecyclerView.ViewHolder{
    TextView dailydatetime,maxmin,dailydescription,dailyprecip,dailyuvcond,dailymorning,dailyafternoon,dailyevening,dailynight;
    ImageView icon;
    public Dailyviewholder(@NonNull View view) {
        super(view);
        dailydatetime = view.findViewById(R.id.daydate);
        maxmin = view.findViewById(R.id.maxmintemp);
        dailydescription = view.findViewById(R.id.Dailydesc);;
        dailyprecip = view.findViewById(R.id.dailyprec);
        dailyuvcond = view.findViewById(R.id.dailyuv);
        dailymorning = view.findViewById(R.id.dailymorning);
        dailyafternoon = view.findViewById(R.id.dailyafternoon);;
        dailyevening = view.findViewById(R.id.dailyevening);
        dailynight = view.findViewById(R.id.dailynight);
        icon = view.findViewById(R.id.imageView);
    }
}
