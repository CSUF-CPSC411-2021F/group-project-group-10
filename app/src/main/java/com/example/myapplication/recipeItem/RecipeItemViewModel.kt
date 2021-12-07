package com.example.myapplication.recipeItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Recipe
import com.example.myapplication.database.RecipeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeItemViewModel(
    val recipeId: Long,
    val database: RecipeDao,
    application: Application
) : AndroidViewModel(application) {

    val recipe = database.get(recipeId)

    fun save() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch(Dispatchers.IO) {
            var rec = Recipe()
            rec.recipeId = recipeId
            rec.name = recipe!!.value!!.name
            rec.ingredients = recipe!!.value!!.ingredients
            rec.instructions = recipe!!.value!!.instructions

            database.update(rec)
        }
    }
}