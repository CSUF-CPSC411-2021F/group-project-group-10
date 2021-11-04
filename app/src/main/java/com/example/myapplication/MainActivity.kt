package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecipeListAdapter.onItemClickListener {
    private val dataset = generateList(20)
    private val adapter = RecipeListAdapter(dataset, this)

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(
            this,
            "Recipe $position was clicked",
            Toast.LENGTH_SHORT).show()
        val clickedItem: Recipe = dataset[position]
        val intent = Intent(this, RecipeInfo::class.java)
        intent.putExtra("Name", clickedItem.rName)
        intent.putExtra("Ingredients", clickedItem.ingredients)
        intent.putExtra("Steps", clickedItem.steps)
        startActivity(intent)
        clickedItem.rName = "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateList(size: Int): MutableList<Recipe>{
        val list = mutableListOf<Recipe>()

        for(i in 0 until size){
            val item = Recipe("Recipe $i", "Ingredient List $i", "Steps for Recipe $i")
            list += item
        }
        return list
    }

}