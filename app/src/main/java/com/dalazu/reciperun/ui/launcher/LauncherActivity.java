package com.dalazu.reciperun.ui.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.dalazu.reciperun.ui.onboard.OnboardActivity;
import com.dalazu.reciperun.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                startActivity(new Intent(LauncherActivity.this, OnboardActivity.class));
                finish();
            }
        };
        handler.postDelayed(r, 1000);

    }
}