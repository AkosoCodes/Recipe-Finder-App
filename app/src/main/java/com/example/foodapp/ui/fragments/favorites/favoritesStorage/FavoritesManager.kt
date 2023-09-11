package com.example.foodapp.ui.fragments.favorites.favoritesStorage

import android.content.Context

class FavoritesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)

    fun getFavoriteRecipeIds(): Set<String> {
        return sharedPreferences.getStringSet("favorite_recipe_ids", HashSet()) ?: HashSet()
    }

    fun addFavorite(recipeId: String) {
        val favoriteRecipeIds = getFavoriteRecipeIds().toMutableSet()
        favoriteRecipeIds.add(recipeId)
        sharedPreferences.edit().putStringSet("favorite_recipe_ids", favoriteRecipeIds).apply()
    }

    fun removeFavorite(recipeId: String) {
        val favoriteRecipeIds = getFavoriteRecipeIds().toMutableSet()
        favoriteRecipeIds.remove(recipeId)
        sharedPreferences.edit().putStringSet("favorite_recipe_ids", favoriteRecipeIds).apply()
    }

    fun isFavorite(recipeId: String): Boolean {
        val favoriteRecipeIds = getFavoriteRecipeIds()
        return recipeId in favoriteRecipeIds
    }
}


