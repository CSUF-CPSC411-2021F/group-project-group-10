package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {
    // Add an intersection entity to a table in the database.
    // We use suspend to run the function asynchronously (coroutine).
    @Insert
    suspend fun insert(recipe: Recipe)

    // Update an intersection entity to a table in the database. Often uses the primary key
    // We use suspend to run the function asynchronously (coroutine).
    @Update
    suspend fun update(recipe: Recipe)

    // Custom query for retrieving a single Intersection from a table in the database using
    // its intersection id. We don't use suspend because LiveData objects are already designed
    // to work asynchronous.
    @Query("SELECT * from recipe_table WHERE recipeId = :key")
    fun get(key: Long): LiveData<Recipe>?

    // Custom query for retrieving all Intersection entities from a table in the database.
    // Data is stored to a List LiveData. We don't use suspend because LiveData objects
    // are already designed to work asynchronously.
    @Query("SELECT * from recipe_table ORDER BY recipeId DESC")
    fun getAllRecipes(): LiveData<List<Recipe>>

    // Custom query for deleting all entities on a table in the database
    // We use suspend to run the function asynchronously (coroutine).
    @Query("DELETE from recipe_table")
    suspend fun clear()

    @Query("DELETE from recipe_table WHERE recipeId = :key")
    suspend fun deleteRecipe(key: Long)
}