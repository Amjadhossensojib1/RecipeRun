package com.dalazu.reciperun.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.model.Food;
import com.dalazu.reciperun.utils.FoodAdapter;
import com.dalazu.reciperun.utils.FoodItemClickListener;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private DatabaseReference databaseReference;

    public FoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        foodList = new ArrayList<>();
        foodAdapter = new FoodAdapter(getContext(), foodList, new FoodAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Food food) {

            }
        });
        recyclerView.setAdapter(foodAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("food");

        loadFoods();

        return view;
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
                    Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }


}
