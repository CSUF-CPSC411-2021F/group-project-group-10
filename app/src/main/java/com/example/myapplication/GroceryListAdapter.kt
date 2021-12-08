package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.Grocery
import com.example.myapplication.databinding.GroceryItemBinding

class GroceryListAdapter(val clickListener: GroceryListener, val gvm: GroceryViewModel) : ListAdapter<Grocery,
        GroceryListAdapter.ItemViewHolder>(GroceryDiffCallback()) {

    class ItemViewHolder(val binding: GroceryItemBinding ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Grocery, clickListener: GroceryListener, groceryViewModel: GroceryViewModel) {
            binding.grocery = item
            binding.clickListener = clickListener
            binding.groceryViewModel = groceryViewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GroceryItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener, gvm)
    }
}

class GroceryDiffCallback : DiffUtil.ItemCallback<Grocery>() {

    override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
        return oldItem.groceryId == newItem.groceryId
    }


    override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
        return oldItem.name == newItem.name && oldItem.information == newItem.information
    }
}

class GroceryListener(val clickListener: (groceryId: Long) -> Unit) {
    fun onClick(grocery: Grocery) = clickListener(grocery.groceryId)
}