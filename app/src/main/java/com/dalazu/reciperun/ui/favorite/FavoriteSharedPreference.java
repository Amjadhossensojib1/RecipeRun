package com.dalazu.reciperun.ui.favorite;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FavoriteSharedPreference {

    private static final String PREF_NAME = "favorite_pref";
    private static final String KEY_FAVORITES = "favorites";

    // Add a favorite item
    public static void addFavorite(Context context, String id) {
        Set<String> favorites = getFavorites(context);
        favorites.add(id);
        saveFavorites(context, favorites);
    }

    // Remove a favorite item
    public static void removeFavorite(Context context, String id) {
        Set<String> favorites = getFavorites(context);
        if (favorites.contains(id)) {
            favorites.remove(id);
            saveFavorites(context, favorites);
        }
    }

    // Get all favorite items
    public static Set<String> getFavorites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> savedSet = prefs.getStringSet(KEY_FAVORITES, null);
        return (savedSet != null) ? new HashSet<>(savedSet) : new HashSet<>();
    }

    // Save updated set
    private static void saveFavorites(Context context, Set<String> favorites) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putStringSet(KEY_FAVORITES, new HashSet<>(favorites)).apply();  // always clone for safety
    }

    // Check if item is favorite
    public static boolean isFavorite(Context context, String id) {
        return getFavorites(context).contains(id);
    }

    // Clear all favorites
    public static void clearFavorites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_FAVORITES).apply();
    }

    // Get favorite count
    public static int getFavoriteCount(Context context) {
        return getFavorites(context).size();
    }

    // Check if any favorites exist
    public static boolean hasFavorites(Context context) {
        return !getFavorites(context).isEmpty();
    }
}
