package com.example.myapplication

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.GroceryDao

/**
 * Generates an GroceryViewModel tied to the database.
 */
class GroceryViewModelFactory(
    private val dataSource: GroceryDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the GroceryViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryViewModel::class.java)) { // ViewModel class
            return GroceryViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}