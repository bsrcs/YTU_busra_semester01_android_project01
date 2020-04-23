package com.busra.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Movie;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SensorListActivity extends AppCompatActivity {
    TextView tvSensorList=null,txtHeading;
    private SensorManager mSensorManager;
    private Sensor lightSensor;
    private float maxValue;
    private  float sensorValue;
    private SensorEventListener lightEventListener;
    RelativeLayout background, itemBackground;

    private List<SensorItem> sensorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SensorListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_sensorlist);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        background=findViewById(R.id.sensorBackground);
        itemBackground=findViewById(R.id.itemBackground);
        txtHeading=findViewById(R.id.txtSensorHeading);
        mAdapter = new SensorListAdapter(sensorList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 1; i < mList.size(); i++) {

            SensorItem sensorItem = new SensorItem(mList.get(i).getName() , mList.get(i).getVendor(), mList.get(i).getVersion()+"");
            sensorList.add(sensorItem);
            mAdapter.notifyDataSetChanged();
        }



        lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            Toast.makeText(this, "The device has no light sensor !", Toast.LENGTH_SHORT).show();
            finish();
        }

        // max value for light sensor
        maxValue = lightSensor.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT) {

                    sensorValue=sensorEvent.values[0];
                    Log.e("SENSOR VALUE", "Value =  " +sensorValue);
                    if(sensorValue <=3){
                        background.setBackgroundColor(Color.parseColor("#000000"));


                       txtHeading.setTextColor(Color.parseColor("#ffffff"));
                    }
                    else{
                       background.setBackgroundColor(Color.parseColor("#ffffff"));

                      txtHeading.setTextColor(Color.parseColor("#000000"));
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(lightEventListener);
    }
}
