package com.busra.testapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorListAdapter extends RecyclerView.Adapter<SensorListAdapter.MyViewHolder> {

    private List<SensorItem> sensorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, vendor, version;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txt_name);
            vendor = (TextView) view.findViewById(R.id.txt_vendor);
            version = (TextView) view.findViewById(R.id.txt_version);
        }
    }


    public SensorListAdapter(List<SensorItem> sensorList) {
        this.sensorList = sensorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensor_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SensorItem sensorItem = sensorList.get(position);
        holder.name.setText(sensorItem.getName());
        holder.vendor.setText(sensorItem.getVendor());
        holder.version.setText((sensorItem.getVersion().toString()));

    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }



}
