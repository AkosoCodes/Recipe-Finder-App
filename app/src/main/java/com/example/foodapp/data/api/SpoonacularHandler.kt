package com.example.foodapp.data.api

import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.models.Result
import com.example.foodapp.utils.Constants.Companion.BASE_URL
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpoonacularHandler(
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create())
        .build(),

    private val api: SpoonacularAPI = retrofit.create(SpoonacularAPI::class.java)
) {

    fun getRecipes(queries: Map<String, String>, callback: Callback<FoodRecipe>){
        api.getRecipes(queries).enqueue(callback)
    }

    fun getRecipeById(recipeId: Int, options: Map<String, String>, callback: Callback<FoodRecipe>){
        api.getRecipeById(recipeId, options).enqueue(callback)
    }
}