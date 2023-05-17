package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FusedLocationProviderClient LocationClient;
    private static final int LOCATION_REQUEST = 111;
    //private List<Mainoffice> list;
    private String locationString = "Unspecified Location";
    private RequestQueue queue;
    ImageView imageview;
    TextView addr;
    private  ArrayList<Mainoffice> List = new ArrayList<>();  // Main content is here
    private RecyclerView recyclerView; // Layout's recyclerview
    private OfficialAdapter mAdapter;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Civil Advocacy");
        addr = findViewById(R.id.location);
        LocationClient =
                LocationServices.getFusedLocationProviderClient(this);
        GetLocation();
        recyclerView = findViewById(R.id.recyler);

        mAdapter = new OfficialAdapter(List, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //List.add(new Mainoffice("gg","gg","gg","gg"));
        //mAdapter.notifyItemRangeChanged(0, List.size());
        //imageview = findViewById(R.id.imageView);
        //performDownload();
        //VolleyLoader.getSourceData(this,locationString);
    }

    private void GetLocation() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        LocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    // Got last known location. In some situations this can be null.
                    if (location != null) {
                        locationString = getPlace(location);
                        //Log.d(TAG, locationString);
                        //textView.setText(locationString);
                    }
                })
                .addOnFailureListener(this, e ->
                        Toast.makeText(MainActivity.this,
                                e.getMessage(), Toast.LENGTH_LONG).show());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    GetLocation();
                } else {
                    //textView.setText(R.string.deniedText);
                }
            }

        }
    }

    private String getPlace(Location loc) {

        StringBuilder sb = new StringBuilder();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            sb.append(city + "," + state);
            locationString = sb.toString();
            //Log.d(TAG, sb.toString());
            //Log.d(TAG, locationString);
            VolleyLoader.getSourceData(this, locationString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //locationString = sb.toString();
        return sb.toString();
    }

    public String getaddress() {
        return locationString;
    }

    public void setaddress(String a) {
        addr.setText(a);
    }

    public void setimage(String url) {
        Picasso.get().load(url.replace("http:", "https:")).into(imageview);
    }

    public void updateData(ArrayList<Mainoffice> cList) {
        // Log.d(TAG, "updateData: Alive"+cList.size());
        clear();
        List.clear();
        List.addAll(cList);
        mAdapter.notifyItemRangeChanged(0, cList.size());
    }
    public void clear()
    {
        List.clear();
    }

    @Override
    public void onClick(View view) {
        int pos = recyclerView.getChildLayoutPosition(view);
        Intent intent = new Intent(this, MainActivity2.class);
        String location1 = locationString;
        String officename = List.get(pos).getOfficename();
        String name = List.get(pos).getName();
        String party = List.get(pos).getparty();
        String address = List.get(pos).getAddress();
        String phone = List.get(pos).getPhone();
        String email = List.get(pos).getEmail();
        String website = List.get(pos).getWebsite();
        String picurl = List.get(pos).getPicurl();
        //Log.d(TAG, "onClick: "+website);
        intent.putExtra("location", location1);
        intent.putExtra("office", officename);
        intent.putExtra("name", name);
        intent.putExtra("party", party);
        intent.putExtra("address", address);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("website", website);
        intent.putExtra("picurl", picurl);
        startActivity(intent);
        //Toast.makeText(this, String.valueOf(pos), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.about_menu) {
            //Toast.makeText(this, "You want to do A", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, about_activity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.search_menu) {
           // Toast.makeText(this, "You have chosen B", Toast.LENGTH_SHORT).show();
            List.clear();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Create an edittext and set it to be the builder's view
            final EditText et = new EditText(this);
            et.setInputType(InputType.TYPE_CLASS_TEXT);
            et.setGravity(Gravity.CENTER_HORIZONTAL);
            builder.setView(et);


            // lambda can be used here (as is below)
            builder.setPositiveButton("OK", (dialog, id) -> {
                List.clear();
                clear();
                VolleyLoader.getSourceData(this, et.getText().toString());
            });

            // lambda can be used here (as is below)
            builder.setNegativeButton("CANCEL", (dialog, id) -> {
                Toast.makeText(MainActivity.this, "You changed your mind!", Toast.LENGTH_SHORT).show();
            });

            builder.setMessage("Please enter a location:");
            //builder.setTitle("Single Input");

            AlertDialog dialog = builder.create();
            dialog.show();
            //VolleyLoader.getSourceData(this, "Chicago");
            return true;
        }  else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void searchButton(){
        VolleyLoader.getSourceData(this, locationString);
    }
}