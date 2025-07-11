package com.dalazu.reciperun.ui.foodbycategoryActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.model.Food;
import com.dalazu.reciperun.utils.BaseActivity;
import com.dalazu.reciperun.utils.FoodAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodByCategoryActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<Food> foodList;
    private FoodAdapter adapter;
    private DatabaseReference databaseReference;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodcategory);

        setupToolbar(R.id.toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        foodList = new ArrayList<>();
        adapter = new FoodAdapter(this, foodList, null);
        recyclerView.setAdapter(adapter);

        selectedCategory = getIntent().getStringExtra("category");
        if (selectedCategory != null) {
            selectedCategory = selectedCategory.trim().toLowerCase();
        } else {
            selectedCategory = "";
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("food");

        loadFoodByCategory();
    }

    private void loadFoodByCategory() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Food food = dataSnapshot.getValue(Food.class);

                    if (food != null && food.getCategory() != null) {
                        String foodCategory = food.getCategory().trim().toLowerCase();

                        if (foodCategory.equals(selectedCategory)) {
                            foodList.add(food);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

                if (foodList.isEmpty()) {
                    Toast.makeText(FoodByCategoryActivity.this, "No items found in category.", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(FoodByCategoryActivity.this, "Found " + foodList.size() + " items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FoodByCategoryActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("FoodCategory", "Database error: " + error.getMessage());
            }
        });
    }
}
