package com.il.mapd721project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends AppCompatActivity {

    Button btnLogout;
    Button btnScan;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnLogout = findViewById(R.id.btn_logout);
        btnScan = findViewById(R.id.scan);
        mAuth = FirebaseAuth.getInstance();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, ScanActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Homepage.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Homepage.this, MainActivity.class));
        }
    }
}