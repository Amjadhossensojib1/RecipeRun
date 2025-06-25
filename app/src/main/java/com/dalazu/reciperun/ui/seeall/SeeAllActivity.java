package com.dalazu.reciperun.ui.seeall;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.model.Category;
import com.dalazu.reciperun.ui.foodbycategoryActivity.FoodByCategoryActivity;
import com.dalazu.reciperun.utils.BaseActivity;
import com.dalazu.reciperun.utils.CategoryAdapter;

import java.util.ArrayList;

public class SeeAllActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Category> categorylist;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        setupToolbar(R.id.material_toolbar);

        recyclerView =findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        categorylist = new ArrayList<>();
        categorylist.add(new Category("https://www.pngarts.com/files/10/Food-Items-PNG-Background-Image.png","Breakfast"));
        categorylist.add(new Category("https://static.vecteezy.com/system/resources/thumbnails/049/423/635/small_2x/a-hamburger-and-fries-on-a-plate-with-a-glass-of-soda-png.png","Brunch "));
        categorylist.add(new Category("https://www.pngall.com/wp-content/uploads/5/Serving-Food-PNG-Image-HD.png","Lunch"));
        categorylist.add(new Category("https://static.vecteezy.com/system/resources/thumbnails/036/512/678/small_2x/ai-generated-top-view-of-a-black-plate-with-indian-food-isolated-on-a-transparent-background-png.png","Snack"));
        categorylist.add(new Category("https://pngimg.com/uploads/noodle/small/noodle_PNG53.png","Supper"));
        categorylist.add(new Category("https://static.vecteezy.com/system/resources/previews/045/730/604/non_2x/tikka-masala-curry-chicken-served-over-rice-in-bowl-traditional-indian-food-png.png","Dinner"));

        adapter = new CategoryAdapter(categorylist, categoryName -> {
            Intent intent = new Intent(this, FoodByCategoryActivity.class);
            intent.putExtra("category", categoryName);
            startActivity(intent);
        }, R.layout.item_layout_seeall);

        recyclerView.setAdapter(adapter);


    }
}