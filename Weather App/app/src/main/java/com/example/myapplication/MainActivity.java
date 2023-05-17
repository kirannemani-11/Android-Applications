package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private long start;

    private EditText editText;
    private TextView textView;
    private ImageView imageView;
    private HourAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private static int m = 0;
    ArrayList<Hour> hours = new ArrayList<>();
    TextView weatherdesc,currdt,degv,wind,feelslik,humidity,uv,visib,sunr,suns,morningtemp,afternoontemp,eveningtemp,nighttemp;
    RecyclerView recyclerView;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(hasNetworkConnection()) {currdt = findViewById(R.id.Dataview);currdt.setText(String.valueOf("No network"));}
        weatherdesc = findViewById(R.id.weatherdescription);
        currdt = findViewById(R.id.Dataview);
        degv = findViewById(R.id.degreeview);
        wind = findViewById(R.id.winds);
        feelslik = findViewById(R.id.Feelslike);
        humidity = findViewById(R.id.Humidity);
        uv = findViewById(R.id.UV);
        visib = findViewById(R.id.Visibility);
        sunr = findViewById(R.id.Sunrise);
        suns = findViewById(R.id.Sunset);
        recyclerView = findViewById(R.id.recyler);
        mAdapter = new HourAdapter(hours,this);

        recyclerView.setAdapter(mAdapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        morningtemp = findViewById(R.id.Morning);
        afternoontemp = findViewById(R.id.Afternoon);
        eveningtemp = findViewById(R.id.Evening);
        nighttemp = findViewById(R.id.Night);
        image = findViewById(R.id.imageView2);
        //textView = findViewById(R.id.textView);
        //queue = Volley.newRequestQueue(this);
        WeatherLoaderVolley.getSourceData(this, "Chicago","us");
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        return true;
    }

    public void setData(String s, String dt, String temp, String windsinfo, String feels, String v,String hum, String UV, String sunrise, String sunset, String address, String icon)
    {
        weatherdesc.setText(s);
        currdt.setText(dt);
        if(this.m==0) {
        degv.setText(String.format("%s°F", temp)); } else {degv.setText(String.format("%s°C", temp)); }
        wind.setText(windsinfo+"mph");
        if(this.m==0) {
        feelslik.setText(String.format("%s°F", feels)); } else {feelslik.setText(String.format("%s°C", feels));}
        humidity.setText(hum);
        if(m==0) {visib.setText(v+"mph");} else {visib.setText(v+"kmph");}
        //visib.setText(v);
        uv.setText(UV);
        sunr.setText(sunrise);
        suns.setText(sunset);
        setTitle(address);
        String iconnn = icon.replace("-","_");
        image.setImageResource(getIcon(iconnn));
        //image.setImageResource(getIcon(hours.get(8).getIcon()));
    }
    public void setdayhour(ArrayList<Hour> a)
    {
        double morning = a.get(8).getTemp();
        double afternoon = a.get(13).getTemp();
        double evening = a.get(17).getTemp();
        double night  = a.get(23).getTemp();
        //String m = String.valueOf(morning);
        if(this.m==0){
            String m = String.valueOf(morning);
        morningtemp.setText(m+"°F");
        m = String.valueOf(afternoon);
        afternoontemp.setText(m+"°F");
        m = String.valueOf(evening);
        eveningtemp.setText(m+"°F");
        m = String.valueOf(night);
        nighttemp.setText(m+"°F"); }
        else {
            String m = String.valueOf(morning);
            morningtemp.setText(m+"°C");
            m = String.valueOf(afternoon);
            afternoontemp.setText(m+"°C");
            m = String.valueOf(evening);
            eveningtemp.setText(m+"°C");
            m = String.valueOf(night);
            nighttemp.setText(m+"°C");
        }
    }
    public void setHourData(ArrayList<Hour> hour)
    {
        hours.addAll(hour);
        mAdapter.notifyItemRangeChanged(0,hours.size());
    }
    public void Onclick(View v)
    {
        String loc = "San Fransisco";
        hours.clear();
        WeatherLoaderVolley.getSourceData(this, loc,"metric");
        mAdapter.notifyItemRangeChanged(0,hours.size());
        //Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        //intent.putExtra(Country.class.getName(), c);
        //startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.changemet) {
            //Toast.makeText(this, "You want to do A", Toast.LENGTH_SHORT).show();
            //textView.setText(R.string.a);

            if(this.m==0) {this.m=1;
                String loc = getTitle().toString();
                hours.clear();
                WeatherLoaderVolley.getSourceData(this, loc,"metric");
                mAdapter.notifyItemRangeChanged(0,hours.size());
            }
            else {this.m=0;
                String loc = getTitle().toString();
                hours.clear();
                WeatherLoaderVolley.getSourceData(this, loc,"us");
                mAdapter.notifyItemRangeChanged(0,hours.size());}
            return true;
        } else if (item.getItemId() == R.id.changemode) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            //intent.putExtra(Country.class.getName(), c);
            startActivity(intent);
            //Toast.makeText(this, "You have chosen B", Toast.LENGTH_SHORT).show();
            //textView.setText(R.string.b);
            return true;
        } else if (item.getItemId() == R.id.changeloc) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Create an edittext and set it to be the builder's view
            final EditText et = new EditText(this);
            et.setInputType(InputType.TYPE_CLASS_TEXT);
            et.setGravity(Gravity.CENTER_HORIZONTAL);
            builder.setView(et);
            builder.setMessage("For us cities: enter as 'city' or 'city','state'");
            builder.setMessage("For International cities: enter as 'city','country'");
            builder.setTitle("Enter a Location");
            builder.setPositiveButton("OK", (dialog, id) -> {String location = et.getText().toString();
                hours.clear();
                WeatherLoaderVolley.getSourceData(this, location,"us");
                m=0;
                mAdapter.notifyItemRangeChanged(0,hours.size());
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> {
                        Toast.makeText(MainActivity.this, "Nothing done", Toast.LENGTH_SHORT).show();
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            //Toast.makeText(this, "C is your selection", Toast.LENGTH_SHORT).show();
            //textView.setText(R.string.c);
            return true;
        }  else {
            return super.onOptionsItemSelected(item);
        }
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
    private boolean hasNetworkConnection() {
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
    public String getadd()
    {
        return getTitle().toString();
    }
}