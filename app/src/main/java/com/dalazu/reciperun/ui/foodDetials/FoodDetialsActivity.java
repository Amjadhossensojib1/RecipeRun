package com.dalazu.reciperun.ui.foodDetials;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.dalazu.reciperun.R;
import com.dalazu.reciperun.ui.video.VideoActivity;
import com.dalazu.reciperun.utils.BaseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodDetialsActivity extends BaseActivity {

    private ImageView imgFood;
    private TextView foodName, fooddsc, min;
    private Button btnWatchVideo;

    private DatabaseReference databaseReference;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detials);

        imgFood = findViewById(R.id.iv_food_img);
        foodName = findViewById(R.id.tv_food_Name);
        fooddsc = findViewById(R.id.tv_fooddsc);
        min = findViewById(R.id.tv_cooking_time);
        btnWatchVideo = findViewById(R.id.btn_watch_video);

        id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("foodName");
        String dsc = getIntent().getStringExtra("fooddsc");
        String image = getIntent().getStringExtra("foodImage");
        String minutes = getIntent().getStringExtra("min");
        String videourl = getIntent().getStringExtra("videoUrl");
        Log.d("URL", "Url: " +videourl);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("food").child(id);

        setupToolbar(R.id.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
        }

        foodName.setText(name);
        fooddsc.setText(dsc);
        min.setText(minutes);
        Glide.with(this).load(image).into(imgFood);



        btnWatchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videourl != null && !videourl.isEmpty()) {
                    Intent intent = new Intent(FoodDetialsActivity.this, VideoActivity.class);
                    intent.putExtra("videoUrl", videourl);

                    startActivity(intent);
                } else {
                    Toast.makeText(FoodDetialsActivity.this, "ভিডিও লিংক পাওয়া যায়নি", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

