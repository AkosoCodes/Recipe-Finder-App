package com.example.foodapp.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.models.Recipe
import com.example.foodapp.ui.fragments.favorites.Favorites
import com.example.foodapp.ui.fragments.recipes.recipeInfo.RecipeInfo
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private val context: Context,
    private val fragmentManager: FragmentManager,
    private var recipes: List<Recipe> = emptyList(), // Initialize with an empty list
    private val favorites: Favorites,

) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val fragment = LayoutInflater.from(context).inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(fragment)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.itemView.setOnClickListener {
            val recipeInfoFragment = RecipeInfo.newInstance(recipe) // Pass the whole recipe object

            fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, recipeInfoFragment) // Replace with your fragment container ID
                .addToBackStack(null) // Optional: Add to the back stack if you want fragment navigation
                .commit()
        }

        holder.titleView?.text = recipe.title

        // Check if the ImageView is null before setting the image resource
        if (holder.imageView != null) {
            Picasso.get().load(recipe.image).into(holder.imageView)
        }

        // Handle adding/removing from favorites
        val favoriteButton = holder.view.findViewById<ImageView>(R.id.favoriteButton)
        if (favorites.isFavorite(recipe)) {
            favoriteButton?.setImageResource(R.drawable.star)
        } else {
            favoriteButton?.setImageResource(R.drawable.instagram)
        }

        favoriteButton?.setOnClickListener {
            if (favorites.isFavorite(recipe)) {
                favorites.removeFavorite(recipe)
                favoriteButton?.setImageResource(R.drawable.star)
            } else {
                favorites.addFavorite(recipe)
                favoriteButton?.setImageResource(R.drawable.instagram)
            }
        }
    }

    fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(
        val view: View,
        val imageView: ImageView? = view.findViewById<ImageView>(R.id.recipe_ImageView),
        val titleView: TextView? = view.findViewById<TextView>(R.id.recipe_Title),
    ) : RecyclerView.ViewHolder(view)
}

