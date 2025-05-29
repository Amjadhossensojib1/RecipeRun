package com.dalazu.reciperun.dashboard;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.ui.favorite.FavoriteFragment;
import com.dalazu.reciperun.ui.home.HomeFragment;
import com.dalazu.reciperun.ui.profile.ProfileFragment;
import com.dalazu.reciperun.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);


    }
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home){
            loadFragment(homeFragment);
            return true;
        }else if (id == R.id.search){
            loadFragment(searchFragment);
            return true;
        }else if (id == R.id.favorite){
            loadFragment(favoriteFragment);
            return true;
        }
        return false;
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
    }
}