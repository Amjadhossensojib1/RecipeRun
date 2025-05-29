package com.dalazu.reciperun.ui.onboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dalazu.reciperun.dashboard.DashboardActivity;
import com.dalazu.reciperun.R;

public class OnboardActivity extends AppCompatActivity {
    private Button btngetStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        btngetStart = findViewById(R.id.btn_getstart);
        btngetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnboardActivity.this, DashboardActivity.class));
            }
        });

    }
}