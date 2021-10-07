package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val recipeListAdapter = RecipeListAdapter(this)
        binding.recyclerView.adapter = recipeListAdapter

        binding.addRecipe.setOnClickListener {
            val toast = Toast.makeText(
                this,
                "Adding ${binding.recipeName.text}",
                Toast.LENGTH_SHORT
            )
            toast.show()

            recipeListAdapter.dataset.add("${binding.recipeName.text}")

            recipeListAdapter.notifyDataSetChanged()

            binding.recipeName.text.clear()
        }
    }
}