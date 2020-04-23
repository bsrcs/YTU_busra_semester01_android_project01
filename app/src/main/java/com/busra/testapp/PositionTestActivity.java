package com.busra.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

public class PositionTestActivity extends AppCompatActivity  {

    private float lastX, lastY, lastZ;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener movementEventListenner;

    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    private float vibrateThreshold = 0;


    private TextView currentX, currentY, currentZ, maxX, maxY, maxZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_test);

        //initializing views
        currentX = (TextView) findViewById(R.id.currentX);
        currentY = (TextView) findViewById(R.id.currentY);
        currentZ = (TextView) findViewById(R.id.currentZ);

        maxX = (TextView) findViewById(R.id.maxX);
        maxY = (TextView) findViewById(R.id.maxY);
        maxZ = (TextView) findViewById(R.id.maxZ);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(movementEventListenner, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        } else {
            // fai! we dont have an accelerometer!
        }
        //setting up a movement listener to track the movement of the device
        movementEventListenner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                // clean current values
                displayCleanValues();
                // display the current x,y,z accelerometer values
                displayCurrentValues();
                // display the max x,y,z accelerometer values
                displayMaxValues();

                // get the change of the x,y,z values of the accelerometer
                deltaX = Math.abs(lastX - event.values[0]);
                deltaY = Math.abs(lastY - event.values[1]);
                deltaZ = Math.abs(lastZ - event.values[2]);

                // if the change is below 2, it is just plain noise
                if (deltaX < 2)
                    deltaX = 0;
                if (deltaY < 2)
                    deltaY = 0;
            //detecting if there is no movement ..no x ,y ,z axis acceleration
                if ((deltaX < 1) &&  (deltaY <  1) && (deltaZ > 9.5)) {
                    Toast.makeText(PositionTestActivity.this, "No movement !", Toast.LENGTH_SHORT).show();
                }
            }

            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

          };
    }
    //onResume() register the accelerometer for listening the events
    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(movementEventListenner, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(movementEventListenner);
    }







    public void displayCleanValues() {
        currentX.setText("0.0");
        currentY.setText("0.0");
        currentZ.setText("0.0");
    }

    // display the current x,y,z accelerometer values
    public void displayCurrentValues() {
        currentX.setText(Float.toString(deltaX));
        currentY.setText(Float.toString(deltaY));
        currentZ.setText(Float.toString(deltaZ));
    }

    // display the max x,y,z accelerometer values
    public void displayMaxValues() {
        if (deltaX > deltaXMax) {
            deltaXMax = deltaX;
            maxX.setText(Float.toString(deltaXMax));
        }
        if (deltaY > deltaYMax) {
            deltaYMax = deltaY;
            maxY.setText(Float.toString(deltaYMax));
        }
        if (deltaZ > deltaZMax) {
            deltaZMax = deltaZ;
            maxZ.setText(Float.toString(deltaZMax));
        }
    }
}
