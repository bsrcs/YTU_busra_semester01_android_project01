package com.busra.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.Toast;

public class MovementActivity extends AppCompatActivity {
    Chronometer mChronometer;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener movementEventListener;
  //  TextView txtTimer;
    CountDownTimer cTimer = null;
    volatile boolean running = true;
    Thread thread;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove the app bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movement);
      //  txtTimer =findViewById(R.id.txtTimerCount);
        handler=new Handler();
         mChronometer=(Chronometer) findViewById(R.id.chronometer);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(movementEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            // fail we dont have an accelerometer!
            Toast.makeText(MovementActivity.this, "No accelerometer found !", Toast.LENGTH_SHORT).show();
        }


        movementEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                // Movement
                float x = values[0];
                float y = values[1];
                float z = values[2];
                float norm_Of_g =(float) Math.sqrt(x * x + y * y + z * z);

                // Normalize the accelerometer vector
                x = (x / norm_Of_g);
                y = (y / norm_Of_g);
                z = (z / norm_Of_g);
              //  int inclination = (int) Math.round(Math.toDegrees(Math.acos(y)));
                int inclination = (int) Math.round(Math.toDegrees(Math.acos(z)));
                Log.i("tag","incline is:"+inclination);

                if (inclination < 25 || inclination > 155 )
                {
                    startTimer();
               }
                else{
                    //txtTimer.setText("");
                    cancelTimer();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(movementEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(movementEventListener);
    }
    void startTimer() {
        // device is flat
        Toast.makeText(MovementActivity.this, "Hmm.. ! Device is in idle state !", Toast.LENGTH_SHORT).show();
        mChronometer.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                mChronometer.stop();
                finish();
            }
        }, 10000);



    }
    //cancel timer
    void cancelTimer() {
       // if(cTimer!=null)
         //   cTimer.cancel();
        if(handler!=null){
            mChronometer.setBase(SystemClock.elapsedRealtime());
            mChronometer.stop();
           // running=false;
            handler.removeCallbacksAndMessages(null);
            Toast.makeText(MovementActivity.this, "ups ! Device is in active state !", Toast.LENGTH_SHORT).show();
        }
    }

    public void myCountDown(){
        //        //  Log.i("test "," inside incline is:"+inclination);
//        cTimer = new CountDownTimer(10000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                thread.start();
//                txtTimer.setText(""+counter);
//                counter++;
//            }
//            public void onFinish() {
//                txtTimer.setText("done!");
//                finish();
//            }
//        }.start();
    }
}
