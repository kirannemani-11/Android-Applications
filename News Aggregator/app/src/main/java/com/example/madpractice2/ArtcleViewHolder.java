package com.example.madpractice2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArtcleViewHolder extends RecyclerView.ViewHolder{
    TextView head;
    TextView date;
    TextView pgno;
    TextView des;
    ImageView img;
    TextView author;
    public ArtcleViewHolder(@NonNull View itemView)
    {
        super(itemView);
        head = itemView.findViewById(R.id.headline);
        date = itemView.findViewById(R.id.time);
        pgno = itemView.findViewById(R.id.pagenum);
        des = itemView.findViewById(R.id.descr);
        img = itemView.findViewById(R.id.imageView);
        author = itemView.findViewById(R.id.authorname);
    }
}
