package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeListAdapter(
    private var dataset: MutableList<Recipe>,
    private val listener: onItemClickListener
) :
    RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val recipe_name: TextView = itemView.findViewById(R.id.recipeName)
        val rIngredients: TextView = itemView.findViewById(R.id.ingredients)
        val recipe_steps: TextView = itemView.findViewById(R.id.steps)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = dataset[position]

        holder.recipe_name.text = currentItem.rName
        holder.rIngredients.text = currentItem.ingredients
        holder.recipe_steps.text = currentItem.steps
    }

    override fun getItemCount() = dataset.size

    class SleepNightListener(val clickListener: (rName: String) -> Unit){
        fun onClick(recipe: Recipe) = clickListener(recipe.rName)
    }

}