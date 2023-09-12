package com.example.foodapp.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.adapters.RecipeAdapter
import com.example.foodapp.data.api.SpoonacularHandler
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.ui.fragments.favorites.Favorites
import com.example.foodapp.ui.fragments.recipes.bottomSheet.RecipesBottomSheet
import com.example.foodapp.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Recipes : Fragment() {

    private var currentQuery: String = ""
    private val handler: SpoonacularHandler = SpoonacularHandler()
    private lateinit var favoritesFragment: Favorites

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        favoritesFragment = Favorites()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recipe_RecyclerView)

        setHasOptionsMenu(true)

        // Load the cached query
        val sharedPreferences = requireActivity().getSharedPreferences("recipes", 0)
        currentQuery = sharedPreferences.getString("query", "").orEmpty()
        fetchRecipes(currentQuery)

        recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(container!!.context)

        val openBottomSheetButton = view.findViewById<FloatingActionButton>(R.id.openBottomSheet)

        openBottomSheetButton.setOnClickListener {
            val bottomSheetFragment = RecipesBottomSheet()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipes_menu, menu)

        val menuSearch = menu.findItem(R.id.menuSearch)

        val menuSearchView = menuSearch.actionView as androidx.appcompat.widget.SearchView

        menuSearchView.isSubmitButtonEnabled = true

        menuSearchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                currentQuery = query.orEmpty()
                fetchRecipes(currentQuery)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                currentQuery = newText.orEmpty()
                return true
            }
        })
    }

    private fun fetchRecipes(query: String) {
        handler.getRecipes(
            mapOf("query" to query, "apiKey" to Constants.API_KEY),
            object : Callback<FoodRecipe> {
                override fun onResponse(call: Call<FoodRecipe>, response: Response<FoodRecipe>) {
                    Log.i("Response", response.body().toString())
                    if (response.isSuccessful) {
                        val foodRecipe: FoodRecipe? = response.body()
                        val adapter = RecipeAdapter(
                            requireContext(),
                            requireFragmentManager(),
                            foodRecipe?.results ?: emptyList(),
                            favoritesFragment
                        )

                        val recyclerView =
                            requireView().findViewById<RecyclerView>(R.id.recipe_RecyclerView)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()

                        // Cache the query
                        val sharedPreferences = requireActivity().getSharedPreferences("recipes", 0)
                        val editor = sharedPreferences.edit()
                        editor.putString("query", query)
                        editor.apply()

                    }
                }

                override fun onFailure(call: Call<FoodRecipe>, t: Throwable) {
                    Log.d("Response", t.message.toString())
                }
            })
    }

}
