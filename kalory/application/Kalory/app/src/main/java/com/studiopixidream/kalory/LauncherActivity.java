package com.studiopixidream.kalory;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Objects.requireNonNull(getSupportActionBar()).hide();



        int SPLASH_TIMEOUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LauncherActivity .this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}