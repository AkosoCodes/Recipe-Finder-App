//package com.example.foodapp.data.database
//
//import kotlinx.coroutines.flow.Flow
//
//class RecipeRepository(private val recipesDAO: RecipesDAO) {
//
//    val allFavoriteRecipes: Flow<List<RecipesEntity>> = recipesDAO.getFavoriteRecipes()
//
//    suspend fun insertFavoriteRecipe(recipe: RecipesEntity) {
//        recipesDAO.addFavoriteRecipe(recipe)
//    }
//
//    suspend fun deleteFavoriteRecipe(recipe: RecipesEntity) {
//        recipesDAO.deleteFavoriteRecipe(recipe)
//    }
//}
