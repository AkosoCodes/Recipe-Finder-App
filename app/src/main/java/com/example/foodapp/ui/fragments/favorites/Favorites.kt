package com.example.foodapp.ui.fragments.favorites

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.adapters.RecipeAdapter
import com.example.foodapp.models.Recipe
import com.example.foodapp.ui.fragments.recipes.Recipes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Favorites : Fragment() {

    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private val favoriteRecipes: MutableList<Recipe> = mutableListOf() // Store favorite recipes
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()
    private lateinit var recipesFragment: Recipes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecyclerView = view.findViewById(R.id.favoritesRecyclerView)
        adapter = RecipeAdapter(requireContext(), requireActivity().supportFragmentManager, favoriteRecipes, this@Favorites)

        favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        favoritesRecyclerView.adapter = adapter

        sharedPreferences = requireActivity().getSharedPreferences("favorites", Context.MODE_PRIVATE)

        loadFavoriteRecipes()

        return view
    }

    private fun loadFavoriteRecipes() {
        // Load the cached favorite recipes
        val recipesJson = sharedPreferences.getString("favorite_recipes", "")
        val type = object : TypeToken<List<Recipe>>() {}.type
        val recipes = gson.fromJson<List<Recipe>>(recipesJson, type)
        if (recipes != null) {
            favoriteRecipes.addAll(recipes)
        }
        adapter.setRecipes(favoriteRecipes)
        adapter.notifyDataSetChanged()

    }

    fun isFavorite(recipe: Recipe): Boolean {
        return favoriteRecipes.contains(recipe)
    }

    fun addFavorite(recipe: Recipe) {
        if (!favoriteRecipes.contains(recipe)) {
            favoriteRecipes.add(recipe)
            saveFavoriteRecipes()
            adapter.setRecipes(favoriteRecipes)
            adapter.notifyDataSetChanged()
            recipesFragment.onFavoritesChanged()
        }
    }

    fun removeFavorite(recipe: Recipe) {
        if (favoriteRecipes.contains(recipe)) {
            favoriteRecipes.remove(recipe)
            saveFavoriteRecipes()
            adapter.setRecipes(favoriteRecipes)
            adapter.notifyDataSetChanged()
            recipesFragment.onFavoritesChanged()
        }
    }

    private fun saveFavoriteRecipes() {
        val recipesJson = gson.toJson(favoriteRecipes)
        sharedPreferences.edit().putString("favorite_recipes", recipesJson).apply()
    }
}
