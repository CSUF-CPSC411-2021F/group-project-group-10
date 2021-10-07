package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeListAdapter (
    private val context: Context,
    var dataset: MutableList<String> = mutableListOf<String>()
) : RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val recipeItemName: TextView = view.findViewById(R.id.recipeItemName)
        //val position: TextView = view.findViewById(R.id.position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.recipeItemName.text = item
    }

    override fun getItemCount(): Int = dataset.size

}