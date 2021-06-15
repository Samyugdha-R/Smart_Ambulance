package com.example.traffic_ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class
adminfome extends AppCompatActivity {
    Button police, ambulance ;
    String uname;
    TextView usertxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminfome);

        police = (Button)findViewById(R.id.disease);
        ambulance = (Button)findViewById(R.id.viewdisease);
        usertxt=(TextView)findViewById(R.id.usertxt);
        Intent intent = getIntent();
        uname = intent.getStringExtra("message");
        usertxt.setText(uname);

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(adminfome.this, police_alert.class);
                // Sending Email to Dashboard Activity using intent.
                intent.putExtra("message", uname);
                intent.putExtra("usertype", "user");
                startActivity(intent);

            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(adminfome.this, useralert.class);
                // Sending Email to Dashboard Activity using intent.
                intent.putExtra("message", uname);
                intent.putExtra("usertype", "user");
                startActivity(intent);

            }
        });
    }
}