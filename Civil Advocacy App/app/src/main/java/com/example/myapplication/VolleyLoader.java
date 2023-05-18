package com.example.myapplication;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleyLoader {
    private static final String TAG = "Volley";
    // Enter Key
    static String DATA_URL = "https://www.googleapis.com/civicinfo/v2/representatives?key=";
    static ArrayList<Mainoffice> list = new ArrayList<Mainoffice>();
    static ArrayList<Officialm2> o = new ArrayList<>();
    //static String ApiKey = "";
    public static void getSourceData(MainActivity mainActivity, String city) {
        //cityy = city;
        RequestQueue queue = Volley.newRequestQueue(mainActivity);
        Uri.Builder buildURL = Uri.parse(DATA_URL).buildUpon();
        //buildURL.appendPath(city);
        buildURL.appendQueryParameter("address", city);
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
       // Log.d(TAG, s);
        try{
        JSONObject jObjMain = new JSONObject(s);
        JSONObject normal = jObjMain.getJSONObject("normalizedInput");
            mainActivity.clear();
        String city = "";
        String line = "";
        String stat = "";
        String zip = "";
        line = normal.getString("line1");
            //Log.d(TAG, s);
        city = normal.getString("city");
        stat = normal.getString("state");
        zip = normal.getString("zip");
        StringBuilder sb = new StringBuilder();
        sb.append(line+city+","+stat+zip);
            //Log.d(TAG, sb.toString());
            mainActivity.setaddress(sb.toString());
            //JSONArray jOffices = jObjMain.getJSONArray("offices");
            //JSONArray jOfficials = jObjMain.getJSONArray("officials");
            //Log.d(TAG, jOfficials.toString());
            //Toast.makeText(mainActivity, officials.toString(), Toast.LENGTH_SHORT).show();
            //mainActivity.setaddress(officials.toString());
            //JSONArray offcialsindice;
            String officename = "";
            JSONArray Offices = jObjMain.getJSONArray("offices");
            JSONArray Officials = jObjMain.getJSONArray("officials");
            for(int i=0;i<Offices.length();i++)
            {
                JSONObject office = Offices.getJSONObject(i);
                officename = office.getString("name");
                //Log.d(TAG, officename);
                JSONArray indices = office.getJSONArray("officialIndices");
                for (int j = 0; j<indices.length(); j++){
                    int pos = Integer.parseInt(indices.getString(j));
                    JSONObject jobjOfficial = Officials.getJSONObject(pos);
                    String officialname = "";
                    String party = "";
                    String picurl = "";
                     String facebook="";
                     String twitter="";
                     String youtube="";
                    officialname = jobjOfficial.getString("name");
                    party = jobjOfficial.getString("party");
                    //Log.d(TAG, "handleResults: ALive");
                    if(jobjOfficial.has("photoUrl"))
                    {
                        picurl = jobjOfficial.getString("photoUrl");
                        //Log.d(TAG, "handleResults: "+picurl);
                    }
                    String address = "";
                    String email = "";
                    String phone = "";
                    String website = "";
                    if (jobjOfficial.has("line1")) address+=jobjOfficial.getString("line1")+'\n';
                    if (jobjOfficial.has("line2")) address+=jobjOfficial.getString("line2")+'\n';
                    if (jobjOfficial.has("line3")) address+=jobjOfficial.getString("line3")+'\n';
                    if (jobjOfficial.has("city")) address+=jobjOfficial.getString("city")+", ";
                    if (jobjOfficial.has("state")) address+=jobjOfficial.getString("state")+' ';
                    if (jobjOfficial.has("zip")) address+=jobjOfficial.getString("zip");
                    //Log.d(TAG, "handleResults: "+address);
                    if (jobjOfficial.has("phones")) phone = jobjOfficial.getJSONArray("phones").getString(0);
                    if (jobjOfficial.has("urls")) website =jobjOfficial.getJSONArray("urls").getString(0);
                    //if (jobjOfficial.has("emails")) email=jobjOfficial.getJSONArray("emails").getString(0);
                    //Log.d(TAG, "handleResults: "+address);
                    /*JSONArray jChannels = jobjOfficial.getJSONArray("channels");
                    for (int k = 0; k<jChannels.length(); k++){
                        JSONObject jChannel = jChannels.getJSONObject(k);
                        if (jChannel.getString("type").equals("Facebook")) facebook=(jChannel.getString("id"));
                        if (jChannel.getString("type").equals("Twitter")) twitter = (jChannel.getString("id"));
                        if (jChannel.getString("type").equals("YouTube")) youtube=(jChannel.getString("id"));
                        Log.d(TAG, "handleResults: "+facebook+twitter+youtube);
                    } */
                    //Log.d(TAG, "handleResults: "+phone);
                    list.add(new Mainoffice(officialname,officename,picurl,party,address,phone,email,website,facebook,twitter,youtube));
                    //Log.d(TAG, "handleResults: Alive");
                    //picurl = jobjOfficial.getString("photoUrl");
                    //Log.d(TAG, "handleResults: "+picurl);
                    //o.add(new Officialm2(officialname,officename,picurl,party,)) */
                }
            }
            //Log.d(TAG, "handleResults: Still Alive");
            //Log.d(TAG, list.toString());
            mainActivity.updateData(list);
            /*for(int i=0; i<jOfficials.length();i++)
            {
                JSONObject obj = jOfficials.getJSONObject(i);
                String name = "";
                String party = "";
                String picurl = "";
                name = obj.getString("name");
                party = obj.getString("party");
                if(obj.has("photoUrl")) {picurl = obj.getString("photoUrl");}
                Log.d(TAG, picurl);
                list.add(new Mainoffice(name,name,picurl,party));
                //mainActivity.setimage(picurl);
               // mainActivity.setofficial(list);
            } */
        }
        catch (Exception e)
        {

        }
    }
}
