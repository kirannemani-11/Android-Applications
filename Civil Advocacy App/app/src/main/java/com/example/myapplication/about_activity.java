package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class about_activity extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        t = findViewById(R.id.civiclink);
    }

    public void civiconclick(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/civic-\n" +
                "information/).")));
    }
}