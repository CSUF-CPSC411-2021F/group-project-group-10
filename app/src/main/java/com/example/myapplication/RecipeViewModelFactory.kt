package com.example.myapplication

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.RecipeDao

class RecipeViewModelFactory(
    private val dataSource: RecipeDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the IntersectionViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) { // ViewModel class
            return RecipeViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}