package com.example.foodapp.ui.fragments.recipes.recipeInfo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
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
import com.example.foodapp.data.database.DatabaseHelper
import com.example.foodapp.models.Recipe
import com.example.foodapp.utils.Constants
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeInfo : Fragment() {

    private val handler: SpoonacularHandler = SpoonacularHandler()
    private var favoritesDatabaseHelper: DatabaseHelper ?= null

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
        
        favoritesDatabaseHelper = DatabaseHelper(requireContext())
        
        if (recipe != null) {
            val recipeId = recipe.id
            val recipeTitle = recipe.title

            fetchRecipeByID(recipeId)
        }

        updateFavoritesButtonState()
        return view
    }

    private fun fetchRecipeByID(query: Int) {
        handler.getRecipeById(query.toInt(), mapOf("apiKey" to Constants.API_KEY), object : Callback<Recipe> {
            @SuppressLint("ResourceAsColor")
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    val recipeDetails: Recipe? = response.body()
                    if (recipeDetails != null) {
                        val recipeImageView = view?.findViewById<ImageView>(R.id.recipeImageView)
                        val recipeTitleTextView = view?.findViewById<TextView>(R.id.recipeTitleTextView)
                        val recipeDescriptionTextView = view?.findViewById<TextView>(R.id.recipeDescriptionTextView)

                        Picasso.get().load(recipeDetails.image).into(recipeImageView)
                        recipeTitleTextView?.text = recipeDetails.title
                        recipeDescriptionTextView?.text = recipeDetails.summary

                        val addToFavoritesButton = view?.findViewById<ImageButton>(R.id.favoriteButton)

                        addToFavoritesButton?.setOnClickListener(){
                            if(favoritesDatabaseHelper?.checkIsFavorite(recipeDetails.id) == false){
                                favoritesDatabaseHelper?.addFavorite(recipeDetails.id, recipeDetails.title, recipeDetails.summary, recipeDetails.image)
                                addToFavoritesButton.setBackgroundColor(R.color.MEDIUM_GRAY)
                                addToFavoritesButton.isEnabled = false

                            }else {
                                favoritesDatabaseHelper?.removeFavorite(recipeDetails.id)
                                addToFavoritesButton.setBackgroundColor(R.color.YELLOW)
                                addToFavoritesButton.isEnabled = true
                            }

                        }
                    } else {
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
            val check = favoritesDatabaseHelper?.checkIsFavorite(recipe.id)
            if(check != null && check){
                addToFavoritesButton?.visibility = View.VISIBLE
            }else {
                addToFavoritesButton?.visibility = View.INVISIBLE
            }
        }
    }
}
