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
    private var recipes: List<Recipe> = emptyList(),
    private val favorites: Favorites
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        // Handle item click to open RecipeInfo fragment
        holder.itemView.setOnClickListener {
            val recipeInfoFragment = RecipeInfo.newInstance(recipe)
            fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, recipeInfoFragment)
                .addToBackStack(null)
                .commit()
        }

        // Set recipe title and ID
        holder.titleView?.text = recipe.title
        holder.idView?.text = "ID: ${recipe.id}"

        // Load recipe image using Picasso
        holder.imageView?.let {
            Picasso.get().load(recipe.image).into(it)
        }

        // Set favorite button based on whether the recipe is a favorite
        val favoriteButton = holder.view.findViewById<ImageView>(R.id.favoriteButton)
        val favoriteIconResource = if (favorites.isFavorite(recipe)) {
            R.drawable.star
        } else {
            R.drawable.instagram
        }
        favoriteButton.setImageResource(favoriteIconResource)

        // Handle favorite button click to add/remove from favorites
        favoriteButton.setOnClickListener {
            if (favorites.isFavorite(recipe)) {
                favorites.removeFavorite(recipe)
                favoriteButton.setImageResource(R.drawable.star)
            } else {
                favorites.addFavorite(recipe)
                favoriteButton.setImageResource(R.drawable.instagram)
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
        val idView: TextView? = view.findViewById<TextView>(R.id.recipeID)
    ) : RecyclerView.ViewHolder(view)
}
