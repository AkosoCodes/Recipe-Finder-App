package com.example.foodapp.data

import com.example.foodapp.data.network.SpoonacularAPI
import com.example.foodapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSrc @Inject constructor(
    private val spoonacularAPI: SpoonacularAPI
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return spoonacularAPI.getRecipes(queries)
    }
}