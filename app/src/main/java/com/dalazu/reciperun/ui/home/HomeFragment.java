package com.dalazu.reciperun.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.ui.viewall.ViewallActivity;
import com.dalazu.reciperun.ui.seeall.SeeAllActivity;
import com.dalazu.reciperun.ui.category.CategoryFragment;
import com.dalazu.reciperun.ui.food.FoodFragment;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
//    private DrawerLayout drawerLayout;
//    private ImageButton imageButton;

    private TextView seeAll,viewall,seemore;
    private ImageSlider imageSlider;

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

//        drawerLayout =view.findViewById(R.id.drawerlayout);
//        imageButton =view.findViewById(R.id.button_drawer);

        seeAll =view.findViewById(R.id.tv_seeall);
        viewall =view.findViewById(R.id.view_all);
        seemore =view.findViewById(R.id.see_more_text);

        DrawerLayout drawerLayout = view.findViewById(R.id.drawerlayout);
        ImageButton menuButton = view.findViewById(R.id.button_menu);

        menuButton.setOnClickListener(v -> {
            if (drawerLayout != null) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewallActivity.class);
                startActivity(intent);
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewallActivity.class);
                startActivity(intent);
            }
        });

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SeeAllActivity.class);
                startActivity(intent);
            }
        });

        imageSlider =view.findViewById(R.id.image_slider);

        ArrayList<SlideModel> arrayList = new ArrayList<>();

        arrayList.add(new SlideModel("https://img.freepik.com/free-psd/food-menu-delicious-pizza-web-banner-template_106176-419.jpg?semt=ais_hybrid&w=740", ScaleTypes.FIT));
        arrayList.add(new SlideModel("https://brotzeit.co/wp-content/uploads/2023/05/LunchMenu-Web-Banner-2.jpg", ScaleTypes.FIT));
        arrayList.add(new SlideModel("https://frybucketbd.com/images/promo/177/burger_banner_2.jpg", ScaleTypes.FIT));
        arrayList.add(new SlideModel("https://www.pizzahutbd.com/attached_images/deals/8/Double-Deal---OLO-Banner-Jan-10.jpg", ScaleTypes.FIT));
        arrayList.add(new SlideModel("https://www.grameenphone.com/_next/image?url=https%3A%2F%2Fcdn01da.grameenphone.com%2Fsites%2Fdefault%2Ffiles%2F2024-07%2FThe_Beyond_Buffet_mobile_image_0.jpg&w=1920&q=75", ScaleTypes.FIT));

        imageSlider.setImageList(arrayList);

        Fragment categoryFragment = new CategoryFragment();
        Fragment foodFragment = new FoodFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.category_container, categoryFragment);
        transaction.add(R.id.food_container, foodFragment);
        transaction.commit();

        return view;
    }
}