package com.dalazu.reciperun.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.model.Food;
import com.dalazu.reciperun.utils.FoodAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<Food> foodList;
    private DatabaseReference databaseReference;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = view.findViewById(R.id.favoriteRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        foodList = new ArrayList<>();

        adapter = new FoodAdapter(getContext(), foodList, food -> {
            if (getContext() == null) return;
            new AlertDialog.Builder(getContext())
                    .setTitle("Remove Favorite")
                    .setMessage("Are you sure you want to remove this item from favorites?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        FavoriteSharedPreference.removeFavorite(getContext(), food.getId());
                        foodList.remove(food);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("food");

        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        if (getContext() == null) return;

        Set<String> favoriteIds = FavoriteSharedPreference.getFavorites(getContext());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Food food = data.getValue(Food.class);
                    if (food != null && favoriteIds.contains(food.getId())) {
                        foodList.add(food);
                    }
                }
                if (isAdded()) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Failed to load favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
