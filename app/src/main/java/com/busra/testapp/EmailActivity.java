package com.busra.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailActivity extends AppCompatActivity {
    Button btnEmail;
    EditText editTo,editSubject,editMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        //initializing views
        editTo=findViewById(R.id.inputEmail);
        editSubject=findViewById(R.id.inputSubject);
        editMessage=findViewById(R.id.inputMessage);
        btnEmail =  findViewById(R.id.btnSendEmail);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });

    }
    //this method is responsible to open the default gmail app
    protected void sendEmail() {
        //storing the to,subject and message content to separate strings
        String To=editTo.getText().toString();
        String Subject=editSubject.getText().toString();
        String Message=editMessage.getText().toString();


        String[] CC = {""};
        //this intent is to open the default email app
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        //passing the to,subject and message content to the default email via intent
        emailIntent.putExtra(Intent.EXTRA_EMAIL, To);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Message);

        try {
            //starting the email app picker dialog
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            //if no email app is installed in the device
            Toast.makeText(EmailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
