package com.example.myapplication.recipeItem

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.RecipeDao

class RecipeItemViewModelFactory(
    private val recipeId: Long,
    private val dataSource: RecipeDao, // Data access object
    private val application: Application
): ViewModelProvider.Factory {

    /**
     * Creates the IntersectionViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeItemViewModel::class.java)) { // ViewModel class
            return RecipeItemViewModel(recipeId, dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}