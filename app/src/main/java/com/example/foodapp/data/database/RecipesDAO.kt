//package com.example.foodapp.data.database
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface RecipesDAO {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addFavoriteRecipe(recipesEntity: RecipesEntity)
//
//    @Query("SELECT * FROM recipes_TABLE ORDER BY id ASC")
//    fun getFavoriteRecipes(): Flow<List<RecipesEntity>>
//
//    @Delete
//    suspend fun deleteFavoriteRecipe(recipesEntity: RecipesEntity)
//
//}