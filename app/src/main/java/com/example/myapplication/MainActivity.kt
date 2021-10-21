package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.GroceryListAdapter
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var count = 0

    /**
     * Sets up the layout and interactions with the main screen of the application.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var groceryRVAdapter = GroceryListAdapter(this)
        binding.groceryList.adapter = groceryRVAdapter


        binding.addIngredient.setOnClickListener {
            count++
            val toast = Toast.makeText(
                this,
                "Adding ${binding.itemName.text}",
                Toast.LENGTH_SHORT
            )

            toast.show()

            // We can access the data through the dataset property inside intersectionListAdapter.the d
            groceryRVAdapter.dataset.add("${count}. ${binding.itemName.text}")

            // Inform the adapter that we made changes so the visual representation can be updated.
            groceryRVAdapter.notifyDataSetChanged()
            binding.itemName.setText("")

        }

    }
}