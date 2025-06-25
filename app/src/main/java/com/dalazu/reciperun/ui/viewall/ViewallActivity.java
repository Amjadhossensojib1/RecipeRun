package com.dalazu.reciperun.ui.viewall;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class ViewallActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);

        setupToolbar(R.id.material_toolbar);

        recyclerView =findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        foodList = new ArrayList<>();
        foodAdapter = new FoodAdapter(this, foodList, new FoodAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Food food) {

            }
        });
        recyclerView.setAdapter(foodAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("food");

        loadFoods();

    }
    private void loadFoods() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Food food = dataSnapshot.getValue(Food.class);
                    foodList.add(food);
                }
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewallActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}