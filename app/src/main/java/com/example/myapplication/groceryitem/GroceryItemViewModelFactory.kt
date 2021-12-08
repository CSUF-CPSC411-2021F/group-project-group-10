package com.example.myapplication.groceryItem

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.GroceryDao


class GroceryItemViewModelFactory(
    private val groceryId: Long,
    private val dataSource: GroceryDao, // Data access object
    private val application: Application
): ViewModelProvider.Factory {

    /**
     * Creates the GroceryItemViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryItemViewModel::class.java)) { // ViewModel class
            return GroceryItemViewModel(groceryId, dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}