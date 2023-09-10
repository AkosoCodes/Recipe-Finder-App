package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.adapters.RecipeAdapter
import com.example.foodapp.data.api.SpoonacularHandler
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Recipes : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

//        val handler : SpoonacularHandler = SpoonacularHandler();
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recipe_RecyclerView)
//
//        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(container!!.context)
//
//        handler.getRecipes(mapOf("query" to "chicken", "apiKey" to Constants.API_KEY), object :
//            Callback<FoodRecipe> {
//            override fun onResponse(call: Call<FoodRecipe>, response: Response<FoodRecipe>) {
//
//                Log.i("Response", response.body().toString())
//                if(response.isSuccessful){
//                    val foodRecipe : FoodRecipe? = response.body()
//                    val adapter = RecipeAdapter(container!!.context, foodRecipe!!.results)
//
//                    recyclerView.adapter = adapter
//
//
//
//                    adapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onFailure(call: Call<FoodRecipe>, t: Throwable) {
//                Log.d("Response", t.message.toString())
//            }
//
//        })

        return view
    }

}