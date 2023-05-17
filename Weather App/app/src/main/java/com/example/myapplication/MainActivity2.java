package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private final ArrayList<Daily> dailyList = new ArrayList<>();
    RecyclerView dailyrec;
    DailyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dailyrec = findViewById(R.id.Dailyrecyler);
        mAdapter = new DailyAdapter(dailyList, this);
        dailyrec.setAdapter(mAdapter);
        dailyrec.setLayoutManager(new LinearLayoutManager(this));
        WeatherLoaderVolley.dailyset(this);
    }
    public void setdailydata(List<Daily> daily)
    {
        dailyList.addAll(daily);
        Log.d(TAG, daily.toString());
        mAdapter.notifyItemRangeChanged(0,dailyList.size());
    }
    public int getIcon(String icon){
        switch (icon){
            case "alert":
                return R.drawable.alert;

            case "clear_day":
                return R.drawable.clear_day;

            case "clear_night":
                return R.drawable.clear_night;

            case "cloudy":
                return R.drawable.cloudy;

            case "fog"  :
                return R.drawable.fog;

            case "hail":
                return R.drawable.hail;

            case "partly_cloudy_day":
                return R.drawable.partly_cloudy_day;

            case "partly_cloudy_night":
                return R.drawable.partly_cloudy_night;

            case "rain":
                return R.drawable.rain;

            case "rain_snow":
                return R.drawable.rain_snow;

            case "rain_snow_showers_day":
                return R.drawable.rain_snow_showers_day;

            case "rain_snow_showers_night":
                return R.drawable.rain_snow_showers_night;

            case "showers_day":
                return R.drawable.showers_day;

            case "showers_night":
                return R.drawable.showers_night;

            case "sleet":
                return R.drawable.sleet;

            case "snow":
                return R.drawable.snow;

            case "snow_showers_day":
                return R.drawable.snow_showers_day;

            case "snow_showers_night":
                return R.drawable.snow_showers_night;

            case "thunder":
                return R.drawable.thunder;

            case "thunder_rain":
                return R.drawable.thunder_rain;

            case "thunder_showers_day":
                return R.drawable.thunder_showers_day;

            case "thunder_showers_night":
                return R.drawable.thunder_showers_night;

            case "wind":
                return R.drawable.wind;

            default:
                return R.drawable.alert;
        } }
        public void settit(String s)
        {
            setTitle(s);
        }
}