package com.dalazu.reciperun.ui.onboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dalazu.reciperun.dashboard.DashboardActivity;
import com.dalazu.reciperun.R;

public class OnboardActivity extends AppCompatActivity {

    private Button btngetStart;
    private SharedPreferences preferences;
    private static final String PREF_NAME = "my_app_pref";
    private static final String FIRST_TIME = "first_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        boolean isFirstTime = preferences.getBoolean(FIRST_TIME, true);

        if (!isFirstTime) {
            goToDashboard();
            finish();
            return;
        }

        setContentView(R.layout.activity_onboard);

        btngetStart = findViewById(R.id.btn_getstart);
        btngetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().putBoolean(FIRST_TIME, false).apply();

                goToDashboard();

                finish();
            }
        });
    }

    private void goToDashboard() {
        Intent intent = new Intent(OnboardActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}
