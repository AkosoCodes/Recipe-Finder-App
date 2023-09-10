package com.example.foodapp.data.api

import com.example.foodapp.models.FoodRecipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SpoonacularAPI {

    @GET("/recipes/complexSearch")
    fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): Call<FoodRecipe>

}