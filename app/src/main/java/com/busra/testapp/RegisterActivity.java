package com.busra.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputName,inputUserName,inputPassword;
    private Button btnRegister;
    private String Name,UserName,Password;
    private Button mLogin;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab=findViewById(R.id.fab);
        inputName = (EditText)findViewById(R.id.name);
        inputUserName = (EditText)findViewById(R.id.username);
        inputPassword = (EditText)findViewById(R.id.password);
        mLogin=findViewById(R.id.btnLinkToLoginScreen);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Hi ! This is busra ! send me bugs !", Toast.LENGTH_SHORT).show();
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name =inputName.getText().toString();
                UserName = inputUserName.getText().toString().trim();
                Password = inputPassword.getText().toString().trim();
                if(LoginHelper.validUserData(RegisterActivity.this,UserName,Password)){

                    LoginHelper.registerUser(RegisterActivity.this, Name, UserName, Password);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

        });
    }





}
