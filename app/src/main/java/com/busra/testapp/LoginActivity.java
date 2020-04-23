package com.busra.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsername,mUserpasswd;
    private Button mLogin;
    private TextView mRegister;
    private String Username,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(LoginHelper.isNewUser(LoginActivity.this)){
            Log.e("NEW_USER_STATUS","This is a New user");
            Toast.makeText(LoginActivity.this, "You need to register first !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }else {
            Log.e("NEW_USER_STATUS","This is a old user !");
            mUsername = (EditText)findViewById(R.id.user_name);
            mUserpasswd = (EditText)findViewById(R.id.user_passwd);
            mLogin = (Button)findViewById(R.id.loginBtn);
            mRegister = (TextView)findViewById(R.id.btnLinkToRegisterScreen);
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Username = mUsername.getText().toString().trim();
                    Password = mUserpasswd.getText().toString().trim();
                    if(LoginHelper.validUserData(LoginActivity.this,Username,Password)){
                        if (LoginHelper.performLogin(LoginActivity.this,Username,Password)){
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            intent.putExtra("message", Username);
                            startActivity(intent);
                            finish();
                        }else {

                            Toast.makeText(getApplicationContext(),"Wrong Username or pass !",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        mUsername.setError("Can't be empty !");
                        mUserpasswd.setError("Can't be empty !");
                        Toast.makeText(getApplicationContext(),"Fields Can't be empty!!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


}

