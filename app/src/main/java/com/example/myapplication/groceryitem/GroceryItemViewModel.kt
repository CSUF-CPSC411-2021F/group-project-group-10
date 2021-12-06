package com.example.myapplication.groceryItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Grocery
import com.example.myapplication.database.GroceryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * IntersectionViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values. It retrieves the corresponding intersection
 * with the provided intersection ID.
 */
class GroceryItemViewModel(
    val groceryId: Long,
    val database: GroceryDao, // Data access object for the Intersection entity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped intersection object given its ID
    val grocery = database.get(groceryId)

//    fun save() {
//        // Launch coroutines in the viewModelScope so that the coroutines are automatically
//        // canceled when the ViewModel is destroyed.
//        viewModelScope.launch(Dispatchers.IO) {
//            var rec = Grocery()
//            rec.information = grocery!!.value!!.information
//
//            database.update(rec)
//        }
//    }
}