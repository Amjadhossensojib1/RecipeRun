package com.dalazu.reciperun.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dalazu.reciperun.R;
import com.dalazu.reciperun.model.Food;
import com.dalazu.reciperun.ui.favorite.FavoriteSharedPreference;
import com.dalazu.reciperun.ui.foodDetials.FoodDetialsActivity;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> foodList;
    private OnItemLongClickListener listener;

    public FoodAdapter(Context context, List<Food> foodList, OnItemLongClickListener listener) {
        this.context = context;
        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.bind(food, context, listener);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
    public void updateList(List<Food> newList) {
        this.foodList = newList;
        notifyDataSetChanged();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood, ivFavorite;
        TextView foodName, foodKal, foodMin;
        RatingBar foodRating;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.iv_foodimg);
            ivFavorite = itemView.findViewById(R.id.iv_favorite);
            foodName = itemView.findViewById(R.id.tv_foodName);
            foodRating = itemView.findViewById(R.id.ratingbar);
            foodKal = itemView.findViewById(R.id.tv_kal);
            foodMin = itemView.findViewById(R.id.tv_min);
        }

        public void bind(Food food, Context context, OnItemLongClickListener listener) {
            Glide.with(context).load(food.getFoodImage()).into(imgFood);
            foodName.setText(food.getFoodName());
            foodKal.setText(food.getTvKal());
            foodMin.setText(food.getTvMin());
            foodRating.setRating(food.getRating() != null ? food.getRating() : 0f);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, FoodDetialsActivity.class);
                intent.putExtra("foodName", food.getFoodName());
                intent.putExtra("fooddsc", food.getFooddsc());
                intent.putExtra("id", food.getId());
                intent.putExtra("foodImage", food.getFoodImage());
                intent.putExtra("min", food.getTvMin());
                intent.putExtra("kal", food.getTvKal());
                intent.putExtra("videoUrl", food.getVideoUrl());
                context.startActivity(intent);
            });

            if (FavoriteSharedPreference.isFavorite(context, food.getId())) {
                ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
            } else {
                ivFavorite.setImageResource(R.drawable.ic_favorite);
            }

            ivFavorite.setOnClickListener(v -> {
                if (FavoriteSharedPreference.isFavorite(context, food.getId())) {
                    FavoriteSharedPreference.removeFavorite(context, food.getId());
                    ivFavorite.setImageResource(R.drawable.ic_favorite);
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    FavoriteSharedPreference.addFavorite(context, food.getId());
                    ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Food food);
    }
}
