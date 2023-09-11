package com.example.foodapp.ui.fragments.recipes.recipeInfo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import com.example.foodapp.R
import com.example.foodapp.data.api.SpoonacularHandler
import com.example.foodapp.models.Recipe
import com.example.foodapp.utils.Constants
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.foodapp.ui.fragments.favorites.favoritesStorage.FavoritesManager

class RecipeInfo : Fragment() {

    private val handler: SpoonacularHandler = SpoonacularHandler()
    private lateinit var favoritesManager: FavoritesManager

    companion object {
        fun newInstance(recipe: Recipe): RecipeInfo {
            val fragment = RecipeInfo()
            val args = Bundle()
            args.putParcelable("recipe", recipe)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_info, container, false)

        val backButton: Button = view.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        val recipe = arguments?.getParcelable<Recipe>("recipe")

        if (recipe != null) {
            val recipeId = recipe.id
            val recipeTitle = recipe.title

            fetchRecipeByID(recipeId)

            val recipeImageView = view?.findViewById<ImageView>(R.id.recipeImageView)
            val recipeTitleTextView = view?.findViewById<TextView>(R.id.recipeTitleTextView)
            val recipeDescriptionTextView = view?.findViewById<TextView>(R.id.recipeDescriptionTextView)

            Picasso.get().load(recipe.image).into(recipeImageView)
            recipeTitleTextView?.text = recipeTitle
            recipeDescriptionTextView?.text = recipe.summary
        }

        val addToFavoritesButton = view.findViewById<ImageButton>(R.id.favoriteButton)
        favoritesManager = FavoritesManager(requireContext())

        addToFavoritesButton.setOnClickListener {
            val recipe = arguments?.getParcelable<Recipe>("recipe")

            if (recipe != null) {
                if (favoritesManager.isFavorite(recipe.id.toString())) {
                    // Recipe is already a favorite, remove it
                    favoritesManager.removeFavorite(recipe.id.toString())
                } else {
                    // Recipe is not a favorite, add it
                    favoritesManager.addFavorite(recipe.id.toString())
                }

                // Update the UI, e.g., change button text or icon
                updateFavoritesButtonState()
            }
        }

        // Update the UI based on whether the recipe is a favorite or not
        updateFavoritesButtonState()

        return view
    }

    private fun fetchRecipeByID(query: Int) {
        handler.getRecipeById(query.toInt(), mapOf("apiKey" to Constants.API_KEY), object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    val recipeDetails: Recipe? = response.body()
                    if (recipeDetails != null) {
                        // Update your UI with the received data (image, title, description)
                        val recipeImageView = view?.findViewById<ImageView>(R.id.recipeImageView)
                        val recipeTitleTextView = view?.findViewById<TextView>(R.id.recipeTitleTextView)
                        val recipeDescriptionTextView = view?.findViewById<TextView>(R.id.recipeDescriptionTextView)

                        // Set image, title, and description based on the API response
                        Picasso.get().load(recipeDetails.image).into(recipeImageView)
                        recipeTitleTextView?.text = recipeDetails.title
                        recipeDescriptionTextView?.text = recipeDetails.summary
                    } else {
                        // Handle the case where the response body is null
                        Log.d("Response", "Recipe details not found")
                    }
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.d("Response", t.message.toString())
            }
        })
    }

    private fun updateFavoritesButtonState() {
        val addToFavoritesButton = view?.findViewById<ImageButton>(R.id.favoriteButton)
        val recipe = arguments?.getParcelable<Recipe>("recipe")

        if (recipe != null) {
            addToFavoritesButton?.isInvisible = favoritesManager.isFavorite(recipe.id.toString())
        }
    }
}
