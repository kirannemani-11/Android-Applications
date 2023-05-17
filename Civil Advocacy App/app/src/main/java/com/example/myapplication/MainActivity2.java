package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {

    String TAG="";
    Mainoffice m;
    TextView locationview;
    String location="";
    String officename="";
    String phone="";
    String picurl="";
    String email="";
    String name="";
    String party="";
    String address="";
    String website="";
    TextView name2;
    TextView officename2;
    TextView party2,websitelink,emailview,phonetext,t4;
    ImageView i,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Civil Advocacy");
        locationview = findViewById(R.id.location2);
        name2 = findViewById(R.id.name2);
        officename2 = findViewById(R.id.officename2);
        party2 = findViewById(R.id.party2);
        i = findViewById(R.id.imageView2);
        i2 = findViewById(R.id.imageView6);
        websitelink = findViewById(R.id.websitelink2);
        emailview = findViewById(R.id.emailtextopen2);
        phonetext = findViewById(R.id.phoneopentext2);
        t4 = findViewById(R.id.textView4);
        Intent intent = getIntent();
        //m = (Mainoffice) intent.getSerializableExtra("data");
        location = intent.getStringExtra("location");
        officename = intent.getStringExtra("office");
        phone = intent.getStringExtra("phone");
        picurl = intent.getStringExtra("picurl");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        party = intent.getStringExtra("party");
        address = intent.getStringExtra("address");
        website = intent.getStringExtra("website");
        name2.setText(name);
        officename2.setText(officename);
        party2.setText(party);
        if(picurl.length()==0){
        Log.d(TAG, "onCreate: "+picurl); }
        if(picurl.length()!=0) {
            Picasso.get().load(picurl.replace("http:", "https:")).error(R.drawable.brokenimage).into(i);}
        if(party!=null)
        {
            if (party.contains("Republican")){
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                i2.setImageResource(R.drawable.rep_logo);
            }
            else if(party.contains("Nonpartisan")) {getWindow().getDecorView().setBackgroundColor(Color.BLACK);i2.setVisibility(View.INVISIBLE);}
            else {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                //i2.setVisibility(View.INVISIBLE);
            }
        }
        websitelink.setText(website);
        Linkify.addLinks(websitelink, Linkify.ALL);
        if(email.length()!=0) {emailview.setText(website); Linkify.addLinks(emailview, Linkify.ALL);}
        else {emailview.setVisibility(View.GONE);}
       phonetext.setText(phone);
        Linkify.addLinks(t4,Linkify.MAP_ADDRESSES);
        Linkify.addLinks(phonetext,Linkify.PHONE_NUMBERS);
        //Log.d(TAG, "onCreate: "+phone);
        //Log.d(TAG, "onCreate: "+officename+phone+picurl+email+name+party+address+website);
        locationview.setText(location);
    }
    public void twitterclick(View v) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("office");
        try {
            getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + name));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + name));
        }
        startActivity(intent);
    }
    public void facebookclick(View v) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("office");
        String FACEBOOK_URL = "https://www.facebook.com/" + name;
        String urlToUse;
        PackageManager packageManager = getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                //newer versions of fb app
                urlToUse = "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                //older versions of fb app
                urlToUse = "fb://page/" + name;
            }     } catch (PackageManager.NameNotFoundException e) {
            urlToUse = FACEBOOK_URL;
            //normal web url
        }
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(urlToUse));
        startActivity(facebookIntent);
    }

    public void youTubeclick(View v) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("office");
        try {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse("https://www.youtube.com/" + name));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/" + name)));
        }
    }
    public void onpicclick(View v) {
        //Intent intent = getIntent();
        if(picurl.length()!=0){
        Intent intent2 = new Intent(this, MainActivity3.class);
        intent2.putExtra("name2",name);
        intent2.putExtra("officename",officename);
        intent2.putExtra("location",location);
            intent2.putExtra("picurl",picurl);
        startActivity(intent2);
    }}
}