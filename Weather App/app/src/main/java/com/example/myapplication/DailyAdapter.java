package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyAdapter extends RecyclerView.Adapter<Dailyviewholder>{
    private final List<Daily> dailyList;
    private final MainActivity2 mainAct2;

    DailyAdapter(List<Daily> empList, MainActivity2 ma) {
        this.dailyList = empList;
        mainAct2 = ma;
    }
    @NonNull
    @Override
    public Dailyviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Log.d(TAG, "onCreateViewHolder: MAKING NEW");

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_view, parent, false);

        return new Dailyviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Dailyviewholder holder, int position) {
        Daily daily = dailyList.get(position);
        //Log.d(TAG, daily.getDailydesc());
        long datetimeEpoch = daily.getDailydatetime();
        Date dateTime = new Date(datetimeEpoch * 1000); // Java time values need milliseconds
        SimpleDateFormat fullDate =
                new SimpleDateFormat("EEE MMM dd h:mm a, yyyy", Locale.getDefault());
        SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm a", Locale.getDefault());
        SimpleDateFormat dayDate = new SimpleDateFormat("EEEE MM/dd", Locale.getDefault());
        SimpleDateFormat req = new SimpleDateFormat("EEE, MMM/yyyy",Locale.getDefault());
        String fullDateStr = fullDate.format(dateTime); // Thu Sep 29 12:00 AM, 2022
        String timeOnlyStr = timeOnly.format(dateTime); // 12:00 AM
        String dayDateStr = dayDate.format(dateTime);
        String requ = req.format(dateTime);
        holder.dailydatetime.setText(requ);
        holder.dailyprecip.setText(String.valueOf(daily.getdailyprec()));
        holder.dailyuvcond.setText(String.valueOf(daily.getDailyuv()));
        holder.dailydescription.setText(daily.getDailydesc());
        holder.dailymorning.setText(String.valueOf(daily.getDailymorn())+"°F");
        holder.dailyafternoon.setText(String.valueOf(daily.getDailyafter())+"°F");
        holder.dailyevening.setText(String.valueOf(daily.getDailyeven())+"°F");
        holder.dailynight.setText(String.valueOf(daily.getDailynight())+"°F");
        Double maxtemp = daily.getdailymaxtemp();
        Double mintemp = daily.getdailymintemp();
        StringBuilder a = new StringBuilder(maxtemp.toString());
        a.append("°F/");
        a.append(mintemp.toString());
        a.append("°F");
        holder.maxmin.setText(a.toString());
        String iconnnn = daily.getIcon();
        iconnnn = iconnnn.replace("-","_");
        holder.icon.setImageResource(mainAct2.getIcon(iconnnn));
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }
}
