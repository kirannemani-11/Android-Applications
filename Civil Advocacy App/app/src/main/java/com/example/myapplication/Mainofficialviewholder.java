package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Mainofficialviewholder extends RecyclerView.ViewHolder{

    TextView name;
    TextView officename;
    TextView party;
    ImageView image;
    public Mainofficialviewholder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        officename = itemView.findViewById(R.id.officename);
        party = itemView.findViewById(R.id.party);
        image = itemView.findViewById(R.id.imageView);
    }
}
