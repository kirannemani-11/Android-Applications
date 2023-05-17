package com.example.myapplication;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherLoaderVolley {

    private static final String TAG = "Download Weather";
    private static final String DATA_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private static final String ApiKey = "3RPL9JUP4ER5A24AN2V78DVAZ";
    private static int m = 0;
    static ArrayList<Daily> dd = new ArrayList<>();
    //String desc = "";
    static String cityy="";
    public static void dailyset(MainActivity2 maa)
    {
        //Log.d(TAG,dd.toString());
        maa.setdailydata(dd);
        maa.setTitle(cityy);
    }
    public static String getDirection(double degrees) {
        if (degrees >= 337.5 || degrees < 22.5)
            return "N";
        if (degrees >= 22.5 && degrees < 67.5)
            return "NE";
        if (degrees >= 67.5 && degrees < 112.5)
            return "E";
        if (degrees >= 112.5 && degrees < 157.5)
            return "SE";
        if (degrees >= 157.5 && degrees < 202.5)
            return "S";
        if (degrees >= 202.5 && degrees < 247.5)
            return "SW";
        if (degrees >= 247.5 && degrees < 292.5)
            return "W";
        if (degrees >= 292.5 && degrees < 337.5)
            return "NW";
        return "X"; // We'll use 'X' as the default if we get a bad value
    }
    public static void getSourceData(MainActivity mainActivity, String city, String datametrics) {
        cityy = city;
        RequestQueue queue = Volley.newRequestQueue(mainActivity);
        Uri.Builder buildURL = Uri.parse(DATA_URL).buildUpon();
        buildURL.appendPath(city);
        if(datametrics.length() == 2) {
        buildURL.appendQueryParameter("unitGroup", "us");
        }
        else { buildURL.appendQueryParameter("unitGroup", "metric"); m =1;}
        buildURL.appendQueryParameter("lang", "en");
        buildURL.appendQueryParameter("key", ApiKey);
        String urlNeeded = buildURL.build().toString();
        Response.Listener<JSONObject> listener =
                response -> handleResults(mainActivity, response.toString());

        Response.ErrorListener error = error1 -> {
            Log.d(TAG, "downloadWeather: Error: " + error1.getMessage());
        };

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlNeeded,
                        null, listener, error);

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    private static void handleResults(MainActivity mainActivity, String s) {
        if (s == null) {
            Log.d(TAG, "handleResults: Failure in data download");
            //mainActivity.downloadFailed();
            return;
        }
        //Log.d(TAG, s);
        String date;
        try {
            JSONObject obj = new JSONObject(s);
            JSONObject currcond = new JSONObject(obj.getJSONObject("currentConditions").toString());
            String  desc = currcond.getString("conditions");
            String cloudc = currcond.getString("cloudcover");
            StringBuilder s1 = new StringBuilder(desc);
            s1.append("("+cloudc+"% clouds)");
            String currdt = currcond.getString("datetimeEpoch");
            long datetimeEpoch = Long.parseLong(currdt);
            Date dateTime = new Date(datetimeEpoch * 1000);
            SimpleDateFormat fullDate =
                    new SimpleDateFormat("EEE MMM dd h:mm a, yyyy", Locale.getDefault());
            SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm a", Locale.getDefault());
            SimpleDateFormat dayDate = new SimpleDateFormat("EEEE MM/dd", Locale.getDefault());
            String fullDateStr = fullDate.format(dateTime); // Thu Sep 29 12:00 AM, 2022
            String timeOnlyStr = timeOnly.format(dateTime); // 12:00 AM
            String dayDateStr = dayDate.format(dateTime); // Thursday 09/29
            String temp = currcond.getString("temp");
            StringBuilder wind = new StringBuilder("Winds: ");
            Double deg = currcond.getDouble("winddir");
            String w = getDirection(deg);
            wind.append(w);
            String windspeed = currcond.getString("windspeed");
            if(m==0){
            //String windspeed = currcond.getString("windspeed");
            wind.append(" at "+windspeed+" mph gusting to ");
            String windgust = currcond.getString("windgust");
            wind.append(windgust); }
            else {wind.append(" at "+windspeed+" kmph gusting to ");
                String windgust = currcond.getString("windgust");
                wind.append(windgust);}
            StringBuilder feelslike = new StringBuilder("Feels like ");
            String feel = currcond.getString("feelslike");
            feelslike.append(feel);
            StringBuilder humidty = new StringBuilder("Humidity: ");
            String hum = currcond.getString("humidity");
            humidty.append(hum+"%");
            StringBuilder visibility = new StringBuilder("Visibility: ");
            String visib = currcond.getString("visibility");
            if(m==0){visibility.append(visib);}
            else {visibility.append(visib);}
            StringBuilder uv = new StringBuilder("UV Index: ");
            String u = currcond.getString("uvindex");
            uv.append(u);
            long sunrise = currcond.getLong("sunriseEpoch");
            long sunset = currcond.getLong("sunsetEpoch");
            Date sunrisedateTime = new Date(sunrise * 1000);
            Date sunsetdateTime = new Date(sunset * 1000);
            String sunrisetimeOnlyStr = timeOnly.format(sunrisedateTime);
            String sunsettimeOnlystr = timeOnly.format(sunsetdateTime);
            StringBuilder sunr = new StringBuilder("Sunrise: ");
            StringBuilder suns = new StringBuilder("Sunset: ");
            sunr.append(sunrisetimeOnlyStr);
            suns.append(sunsettimeOnlystr);
            String address = obj.getString("address");
            JSONArray dailydaily = obj.getJSONArray("days");
            JSONObject dailynow = dailydaily.getJSONObject(0);
            String iconn = dailynow.getString("icon");
            //Log.d(TAG, iconn);
            mainActivity.setData(s1.toString(),fullDateStr,temp,wind.toString(), feelslike.toString(),visibility.toString(),humidty.toString(),uv.toString(),sunr.toString(),suns.toString(),address,iconn);
            //Log.d(TAG, deg.toString());
            ArrayList<Hour> a = new ArrayList<Hour>();
            //Log.d(TAG, daycon.toString())
            //JSONArray Main = new JSONArray(s);
            //JSONArray mainarray = new JSONArray(s);
            JSONArray daily = obj.getJSONArray("days");
            for(int i=0;i<daily.length();i++)
            {
                JSONObject dailycon = daily.getJSONObject(i);
                JSONArray hourcon = dailycon.getJSONArray("hours");
                for (int j = 0; j < hourcon.length(); j++) {
                    int datetimeEpochHourly = hourcon.getJSONObject(j).getInt("datetimeEpoch");
                    double tempHourly = hourcon.getJSONObject(j).getDouble("temp");
                    String conditionsHourly = hourcon.getJSONObject(j).getString("conditions");
                    String iconHourly = hourcon.getJSONObject(j).getString("icon");

                    Hour hourCondition = new Hour(datetimeEpochHourly, tempHourly, conditionsHourly, iconHourly);
                    a.add(hourCondition);
                   // Log.d(TAG, hourcon.toString());
                }
            }
            ArrayList<Daily> d = new ArrayList<>();
            for(int i=0;i<daily.length();i++)
            {
                JSONObject dailycond = daily.getJSONObject(i);
                long dailydatetime;
                Double dailymaxtemp;
                Double dailymintemp;
                String dailydesc;
                int dailyprec=0;
                int dailyuv=0;
                String icon="";
                dailydatetime = dailycond.getLong("datetimeEpoch");
                dailymaxtemp = dailycond.getDouble("tempmax");
                dailymintemp = dailycond.getDouble("tempmin");
                dailydesc = dailycond.getString("description");
                dailyprec = dailycond.getInt("precipprob");
                dailyuv = dailycond.getInt("uvindex");
                icon = dailycond.getString("icon");
                int dailymorn=0;
                int dailyafter=0;
                int dailyeven=0;
                int dailynight=0;
                JSONArray hourcon = dailycond.getJSONArray("hours");
                for (int j = 0; j < hourcon.length(); j++) {
                    String iconnnn = hourcon.getJSONObject(j).getString("icon");
                    //Log.d(TAG, iconnnn);
                    if(j==8) { dailymorn=hourcon.getJSONObject(j).getInt("temp");}
                    if(j==13) { dailyafter=hourcon.getJSONObject(j).getInt("temp");}
                    if(j==17) { dailyeven=hourcon.getJSONObject(j).getInt("temp");}
                    if(j==23) { dailynight=hourcon.getJSONObject(j).getInt("temp");}
                }
                //Log.d(TAG, dailydesc);
                d.add(new Daily(dailydatetime,dailymaxtemp,dailymintemp,dailydesc,dailyprec,dailyuv,icon,dailymorn,dailyafter,dailyeven,dailynight));
                dd.add(new Daily(dailydatetime,dailymaxtemp,dailymintemp,dailydesc,dailyprec,dailyuv,icon,dailymorn,dailyafter,dailyeven,dailynight));
            }
            mainActivity.setdayhour(a);
            mainActivity.setHourData(a);
            //Log.d(TAG, daily.toString());
            //Log.d(TAG, hourcon.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        //mainActivity.setData(desc);
    }
}