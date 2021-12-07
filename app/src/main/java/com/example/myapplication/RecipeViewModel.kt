package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Recipe
import com.example.myapplication.database.RecipeDao
import kotlinx.coroutines.launch

class RecipeViewModel(
    val database: RecipeDao,
    application: Application
) : AndroidViewModel(application) {

    var name = MutableLiveData("")
    var ingredients = MutableLiveData("")
    var instructions = MutableLiveData("")

    val recipeList = database.getAllRecipes()

    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            var recipe = Recipe()
            recipe.name = "New Recipe"
            recipe.ingredients = ""
            recipe.instructions = ""

            // Insert data to the database using the insert coroutine.
            database.insert(recipe)
        }
    }

    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }

    fun delete(recipe: Recipe) {
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.deleteRecipe(recipe.recipeId)
        }
    }
}