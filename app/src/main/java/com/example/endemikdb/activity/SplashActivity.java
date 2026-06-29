package com.example.endemikdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.endemikdb.R;
import com.example.endemikdb.database.DataSeeder;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Seed data di background
        DataSeeder.seedData(this);

        // Delay 2.5 detik lalu ke MainActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, com.example.endemikdb.activity.MainActivity.class));
            finish();
        }, 2500);
    }
}