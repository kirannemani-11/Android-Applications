package com.example.madpractice2;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsLoaderVolley {
    static String s1="gg";
    private static final String TAG = "Download News";
    private static final String DATA_URL = "https://newsapi.org/v2/sources?apiKey=7409f1d65ca84767b73c07c0c0f28a8b";
    private static final String ApiKey = "7409f1d65ca84767b73c07c0c0f28a8b";
    private static final String DATA_URL2 = "https://newsapi.org/v2/top-headlines";
    public static void getSourceData(MainActivity mainActivity) {
        RequestQueue queue = Volley.newRequestQueue(mainActivity);
        Uri.Builder buildURL = Uri.parse(DATA_URL).buildUpon();
       /* buildURL.appendPath(city);
        buildURL.appendQueryParameter("lang", "en");
        buildURL.appendQueryParameter("key", ApiKey);*/
        String urlNeeded = buildURL.build().toString();
        Response.Listener<JSONObject> listener =
                response -> handleResults(mainActivity, response.toString());

        Response.ErrorListener error = error1 -> {
            Log.d(TAG, "downloadWeather: Error: " + error1.getMessage());
        };

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlNeeded,
                        null, listener, error){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("User-Agent", "News-App");
                        return headers;
                    }
                };
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        Log.d(TAG, "getSourceData: STill Alive"+s1);
    }
    private static void handleResults(MainActivity mainActivity, String s) {
        if (s == null) {
            //Log.d(TAG, "handleResults: Failure in data download");
            //mainActivity.downloadFailed();
            return;
        }
        s1=s;
        Log.d(TAG, "handleResults: ");
        try {
            JSONObject obj = new JSONObject(s);
            JSONArray sources = obj.getJSONArray("sources");
            //Log.d(TAG, "handleResults: "+sources.toString());
            //Toast.makeText(mainActivity, sources.toString(), Toast.LENGTH_SHORT).show();
            ArrayList<String> name = new ArrayList<>();
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> cat = new ArrayList<>();
            for(int i=0;i< sources.length();i++)
            {
                JSONObject sourceobj = sources.getJSONObject(i);
                name.add(sourceobj.getString("name"));
                id.add(sourceobj.getString("id"));
                cat.add(sourceobj.getString("category"));
            }
            //Log.d(TAG, "handleResults: "+name.toString());
            //Toast.makeText(mainActivity, name.toString(), Toast.LENGTH_SHORT).show();
            mainActivity.setname(name,id,cat);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void getSourceData2(MainActivity mainActivity, String id) {
        RequestQueue queue = Volley.newRequestQueue(mainActivity);
        Uri.Builder buildURL = Uri.parse(DATA_URL2).buildUpon();
        buildURL.appendQueryParameter("sources", id);
        buildURL.appendQueryParameter("apiKey", ApiKey);
        String urlNeeded = buildURL.build().toString();
        //Log.d(TAG, "getSourceData2: "+urlNeeded);
        Response.Listener<JSONObject> listener =
                response -> handleResults2(mainActivity, response.toString());

        Response.ErrorListener error = error1 -> {
            Log.d(TAG, "downloadWeather: Error: " + error1.getMessage());
        };

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlNeeded,
                        null, listener, error){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("User-Agent", "News-App");
                        return headers;
                    }
                };
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    private static void handleResults2(MainActivity mainActivity, String s) {
        if (s == null) {
            //Log.d(TAG, "handleResults: Failure in data download");
            //mainActivity.downloadFailed();
            return;
        }
        //mainActivity.settext(s);
        ArrayList<Arcticledata> a = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(s);
            JSONArray art = obj.getJSONArray("articles");
            for(int i=0; i< art.length();i++)
            {
                JSONObject article = art.getJSONObject(i);
                String author = article.getString("author");
                String title = article.getString("title");
                String description = article.getString("description");
                String url = article.getString("url");
                String urlToImage = article.getString("urlToImage");
                String publishedAt = article.getString("publishedAt");
                a.add(new Arcticledata(author,title,description,url,urlToImage,publishedAt));
            }
            mainActivity.setartdata(a);
            //mainActivity.settext(s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
