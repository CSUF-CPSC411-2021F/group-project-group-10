package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.Recipe
import com.example.myapplication.databinding.RecipeListItemBinding


class RecipeListAdapter(val clickListener: RecipeListener,val rvm: RecipeViewModel) : ListAdapter<Recipe,
        RecipeListAdapter.ItemViewHolder>(RecipeDiffCallback()) {

    class ItemViewHolder (val binding: RecipeListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Recipe, clickListener: RecipeListener, recipeViewModel: RecipeViewModel) {
            binding.recipe = item
            binding.clickListener = clickListener
            binding.recipeViewModel = recipeViewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecipeListItemBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener, rvm)
    }

}

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.recipeId == newItem.recipeId
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.name == newItem.name && oldItem.ingredients == newItem.ingredients && oldItem.instructions == newItem.instructions
    }
}

class RecipeListener(val clickListener: (recipeId: Long) -> Unit) {
    fun onClick(recipe: Recipe) = clickListener(recipe.recipeId)
}