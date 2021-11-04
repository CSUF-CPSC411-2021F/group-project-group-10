package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private val context: Context,
                    var data:  MutableList<String> = mutableListOf<String>()
                    ): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    interface OnClickListener{

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_reviews, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
    }

    override fun getItemCount() = data.size


}
