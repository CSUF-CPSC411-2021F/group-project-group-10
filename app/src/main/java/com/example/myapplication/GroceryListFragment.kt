package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.database.GroceryDatabase
import com.example.myapplication.databinding.GroceryListFragmentBinding

class GroceryListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: GroceryListFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.grocery_list_fragment, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        val dataSource = GroceryDatabase.getInstance(application).groceryDao

        val viewModelFactory = GroceryViewModelFactory(dataSource, application)

        val groceryViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(GroceryViewModel::class.java)

        binding.groceryViewModel = groceryViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        var groceryAdapter = GroceryListAdapter(GroceryListener {
                groceryId ->
            // Navigate to the intersection view and provide the id of the intersection referenced
            // by the select RecyclerView item.
            this.findNavController().navigate(
                GroceryListFragmentDirections
                    .actionGroceryListFragmentToGroceryItemFragment(groceryId)
            )
        })
        binding.groceryRecyclerview.adapter = groceryAdapter

        groceryViewModel.groceryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                groceryAdapter.submitList(it)
            }
        })
        return binding.root
    }
}