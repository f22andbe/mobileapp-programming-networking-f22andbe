package com.example.networking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MountainViewAdapter extends RecyclerView.Adapter<MountainViewAdapter.MountainViewHolder> {

    Context context;
    ArrayList<Mountain> mountainArrayList;

    public MountainViewAdapter(Context context, ArrayList<Mountain> mountainArrayList) {
        this.context = context;
        this.mountainArrayList = mountainArrayList;
    }

    @Override
    public MountainViewAdapter.MountainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_layout, parent, false);

        return new MountainViewAdapter.MountainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MountainViewHolder holder, int position) {
        holder.mountainName.setText(mountainArrayList.get(position).getName());
        holder.mountainLocation.setText(mountainArrayList.get(position).getLocation());
        Log.d("location in onBindViewHolder", mountainArrayList.get(position).getLocation());
        Log.d("location in textView mountLocation", holder.mountainLocation.getText().toString());
        holder.mountainLocation.setText(String.valueOf(mountainArrayList.get(position).getHeight()) );
    }

    public int getItemCount() {
        return mountainArrayList.size();
    }
    public static class MountainViewHolder extends RecyclerView.ViewHolder {

        TextView mountainName;
        TextView mountainLocation;
        TextView mountainHeight;

        public MountainViewHolder(@NonNull View itemView) {
            super(itemView);
            mountainName = itemView.findViewById(R.id.mountainname);
            mountainLocation = itemView.findViewById(R.id.mountainlocation);
            mountainHeight = itemView.findViewById(R.id.mountainheight);
        }
    }
}
