package com.busra.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView welcomeUser;
    Button btnSensorList,btnMovement,btnOpenEmail,btnOpenPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //initializing views
        welcomeUser=findViewById(R.id.textWelcome);
        btnSensorList=findViewById(R.id.btnSensorList);
        btnMovement=findViewById(R.id.btnMovement);
        btnOpenEmail=findViewById(R.id.openEmailScreen);
        btnOpenPosition=findViewById(R.id.btnPosition);

        //opening Position change activity
        btnOpenPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMovementTestActivity= new Intent(HomeActivity.this, PositionTestActivity.class);
                startActivity(openMovementTestActivity);
            }
        });

        //opening email sending activity
        btnOpenEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openEmailActivity= new Intent(HomeActivity.this,EmailActivity.class);
                startActivity(openEmailActivity);
            }
        });

        //receiving the username from login page to display welcome text
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        welcomeUser.setText("Welcome"+" "+ message + "!");

        //opening sensor List activity
        btnSensorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSensorListActivity= new Intent(HomeActivity.this,SensorListActivity.class);
                startActivity(openSensorListActivity);
            }
        });

        //opening movement activity
        btnMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMovementTestActivity= new Intent(HomeActivity.this, MovementActivity.class);
                startActivity(openMovementTestActivity);
            }
        });


    }
}
