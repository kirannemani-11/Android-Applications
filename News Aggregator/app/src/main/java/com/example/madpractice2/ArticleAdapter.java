package com.example.madpractice2;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArtcleViewHolder>{
    String TAG = "";
    ArrayList<Arcticledata> a = new ArrayList<>();
    private final MainActivity mainActivity;
    public ArticleAdapter(MainActivity mainActivity,ArrayList<Arcticledata> a)
    {
        this.a = a;
        this.mainActivity = mainActivity;
    }
    @NonNull
    @Override
    public ArtcleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtcleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtcleViewHolder holder, int position) {
            Arcticledata sa = a.get(position);
        Log.d(TAG, "onBindViewHolder: "+"ALive Maybe");
            holder.author.setText(sa.getautho());
            if(sa.getautho().equals("null")) {holder.author.setText("");}
            holder.head.setText(sa.getTitle());
            holder.des.setText(sa.getDesc());
        holder.pgno.setText(String.valueOf(position+1)+" of "+String.valueOf(a.size()));
        Picasso picasso = Picasso.with(mainActivity);
        picasso.setLoggingEnabled(true);
        if(sa.getUrltoimage().length()!=0){
        picasso.load(sa.getUrltoimage()).placeholder(R.drawable.loading).error(R.drawable.brokenimage).into(holder.img); }
        else {
            picasso.load(R.drawable.noimage).into(holder.img);
        }
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(sa.getUrl()));
                mainActivity.startActivity(intent);
            }
        });
        holder.date.setText(sa.getPublishedat());
    }

    @Override
    public int getItemCount() {
        return a.size();
    }
}
