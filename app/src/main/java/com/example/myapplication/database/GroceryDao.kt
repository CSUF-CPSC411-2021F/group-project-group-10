package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Data access object for the Grocery entity. The class describes how data is
 * stored, updated, retrieved, or deleted from the database.
 */
@Dao
interface GroceryDao {
    // Add an Grocery entity to a table in the database.
    // We use suspend to run the function asynchronously (coroutine).
    @Insert
    suspend fun insert(grocery: Grocery)

    // Update an Grocery entity to a table in the database. Often uses the primary key
    // We use suspend to run the function asynchronously (coroutine).
    @Update
    suspend fun update(grocery: Grocery)

    // Custom query for retrieving a single Grocery from a table in the database using
    // its Grocery id. We don't use suspend because LiveData objects are already designed
    // to work asynchronous.
    @Query("SELECT * from grocery_table WHERE groceryId = :key")
    fun get(key: Long): LiveData<Grocery>?

    // Custom query for retrieving all Grocery entities from a table in the database.
    // Data is stored to a List LiveData. We don't use suspend because LiveData objects
    // are already designed to work asynchronously.
    @Query("SELECT * from grocery_table ORDER BY groceryId DESC")
    fun getAllGroceries(): LiveData<List<Grocery>>

    // Custom query for deleting all entities on a table in the database
    // We use suspend to run the function asynchronously (coroutine).
    @Query("DELETE from grocery_table")
    suspend fun clear()
}