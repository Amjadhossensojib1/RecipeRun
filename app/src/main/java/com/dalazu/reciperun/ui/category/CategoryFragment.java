package com.dalazu.reciperun.ui.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalazu.reciperun.ui.foodbycategoryActivity.FoodByCategoryActivity;
import com.dalazu.reciperun.R;
import com.dalazu.reciperun.model.Category;
import com.dalazu.reciperun.utils.CategoryAdapter;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Category> categorylist;
    private CategoryAdapter adapter;


    public CategoryFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        categorylist = new ArrayList<>();
        categorylist.add(new Category("https://www.pngarts.com/files/10/Food-Items-PNG-Background-Image.png","Breakfast"));
        categorylist.add(new Category("https://static.vecteezy.com/system/resources/thumbnails/049/423/635/small_2x/a-hamburger-and-fries-on-a-plate-with-a-glass-of-soda-png.png","Brunch "));
        categorylist.add(new Category("https://www.pngall.com/wp-content/uploads/5/Serving-Food-PNG-Image-HD.png","Lunch"));
        categorylist.add(new Category("https://static.vecteezy.com/system/resources/thumbnails/036/512/678/small_2x/ai-generated-top-view-of-a-black-plate-with-indian-food-isolated-on-a-transparent-background-png.png","Snack"));
        categorylist.add(new Category("https://pngimg.com/uploads/noodle/small/noodle_PNG53.png","Supper"));
        categorylist.add(new Category("https://static.vecteezy.com/system/resources/previews/045/730/604/non_2x/tikka-masala-curry-chicken-served-over-rice-in-bowl-traditional-indian-food-png.png","Dinner"));

        adapter = new CategoryAdapter(categorylist, categoryName -> {
            Intent intent = new Intent(getActivity(), FoodByCategoryActivity.class);
            intent.putExtra("category", categoryName);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}