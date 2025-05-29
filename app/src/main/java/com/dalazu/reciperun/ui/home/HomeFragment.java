package com.dalazu.reciperun.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.ui.category.CategoryFragment;
import com.dalazu.reciperun.ui.food.FoodFragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Fragment categoryFragment = new CategoryFragment();
        Fragment foodFragment = new FoodFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.category_container, categoryFragment);
        transaction.add(R.id.food_container, foodFragment);
        transaction.commit();

        return view;
    }
}