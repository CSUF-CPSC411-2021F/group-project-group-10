package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Grocery
import com.example.myapplication.database.GroceryDao
import kotlinx.coroutines.launch


class GroceryViewModel(
    val database: GroceryDao, // Data access object for the Grocery entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.
    var name = MutableLiveData("")
    var information = MutableLiveData("")

    val groceryList = database.getAllGroceries()

    /**
     * Inserts the Grocery object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Grocery object using data stored in the EditText views
            var grocery = Grocery()
            grocery.name = name.value.toString()
            grocery.information = information.value.toString()
            // Insert data to the database using the insert coroutine.
            database.insert(grocery)
        }
    }

    /**
     * Deletes all Grocery entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }

    fun delete(grocery: Grocery) {
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.deleteGrocery(grocery.groceryId)
        }
    }

}