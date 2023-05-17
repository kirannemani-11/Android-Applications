package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity3 extends AppCompatActivity {

    String TAG="";
    String name="";
    String officename="";
    String pic="";
    String picurl="";
    String location="";
    TextView t1,t2,t3;
    ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t1 = findViewById(R.id.location3);
        t2 = findViewById(R.id.name3);
        t3 = findViewById(R.id.officename3);
        i1 = findViewById(R.id.imageView8);
        Intent intent;
        intent = getIntent();
        name = intent.getStringExtra("name2");
        officename = intent.getStringExtra("officename");
        picurl = intent.getStringExtra("picurl");
        location = intent.getStringExtra("location");
        t1.setText(location);
        t2.setText(name);
        t3.setText(officename);
        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        Picasso.get().load(picurl.replace("http:", "https:")).error(R.drawable.brokenimage).into(i1);
        //Log.d(TAG, "onCreate: "+name);
    }
}