package com.dalazu.reciperun.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> categoryList;
    private CategoryClickListener listener;
    private int layoutId;


    public CategoryAdapter(List<Category> categoryList, CategoryClickListener listener, int layoutId) {
        this.categoryList = categoryList;
        this.listener = listener;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bind(category);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private TextView categoryName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.iv_category_img);
            categoryName = itemView.findViewById(R.id.tv_category_Name);

        }

        public void bind(Category category) {
            Picasso.get().load(category.getCategoryImg()).into(categoryImage);
            categoryName.setText(category.getCategoryName());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCategoryClick(category.getCategoryName());
                }
            });
        }
    }
}
