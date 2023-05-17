package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OfficialAdapter extends RecyclerView.Adapter<Mainofficialviewholder>{
    private static final String TAG = "CountryAdapter";
    private final List<Mainoffice> officeList;
    private final MainActivity mainAct;

    OfficialAdapter(List<Mainoffice> empList, MainActivity ma) {
        this.officeList = empList;
        mainAct = ma;
    }
    @NonNull
    @Override
    public Mainofficialviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.official_view, parent, false);

        itemView.setOnClickListener(mainAct);
        //itemView.setOnLongClickListener(mainAct);

        return new Mainofficialviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Mainofficialviewholder holder, int position) {
            Mainoffice m = officeList.get(position);
            holder.party.setText('('+m.getparty()+')');
            holder.name.setText(m.getName());
            holder.officename.setText(m.getOfficename());
        //Log.d(TAG, "onBindViewHolder: "+m.getPicurl());
            if(m.getPicurl()!="") {
                Picasso.get().load(m.getPicurl().replace("http:", "https:")).error(R.drawable.brokenimage).into(holder.image);
            }
    }

    @Override
    public int getItemCount() {
        return officeList.size();
    }
}
