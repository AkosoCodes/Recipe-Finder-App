package com.example.foodapp.utils

class Constants {

    companion object {

        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY = "0b4ba7191f9b4f8293ebcfad2f668160"
        // Alternate API Key
        // c94217cf810346ff983bed0f673ee62d
        // 1a6fba3028ab40348c88ef9dfceeb099
        // 0b4ba7191f9b4f8293ebcfad2f668160

        // API Query Keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"
        const val QUERY_SEARCH = "query"

        // Bottom Sheet
        const val DEFAULT_RECIPES_NUMBER = "50"
        const val DEFAULT_RECIPES_TYPE = "main course"
        const val DEFAULT_RECIPES_DIET = "gluten free"

        // Database
        const val DATABASE_NAME = "recipes_DB"


    }

}