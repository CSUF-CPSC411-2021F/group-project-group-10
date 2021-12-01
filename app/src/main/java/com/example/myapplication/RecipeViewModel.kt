package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Recipe
import com.example.myapplication.database.RecipeDao
import kotlinx.coroutines.launch

class RecipeViewModel(
    val database: RecipeDao, // Data access object for the Intersection entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.
    var name = MutableLiveData("")
    var ingredients = MutableLiveData("")
    var instructions = MutableLiveData("")

    // Retrieves all Intersection objects from the database
    // Represented as a LiveData<List<Intersection>>
    val recipeList = database.getAllRecipes()

    /**
     * Inserts the Intersection object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Intersection object using data stored in the EditText views
            var recipe = Recipe()
            recipe.name = "New Recipe"
            recipe.ingredients = "Recipe Ingredients"
            recipe.instructions = "Recipe Instructions"

            // Insert data to the database using the insert coroutine.
            database.insert(recipe)
        }
    }

    /**
     * Deletes all Intersection entities in the database.
     */
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