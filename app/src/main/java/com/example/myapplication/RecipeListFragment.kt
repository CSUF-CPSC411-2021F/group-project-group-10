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
import com.example.myapplication.database.RecipeDatabase
import com.example.myapplication.databinding.RecipeListFragmentBinding

class RecipeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: RecipeListFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.recipe_list_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = RecipeDatabase.getInstance(application).recipeDao

        val viewModelFactory = RecipeViewModelFactory(dataSource, application)

        val recipeViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(RecipeViewModel::class.java)

        binding.recipeViewModel = recipeViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        // Provide a lambda function that is called when the RecyclerView item is selected.
        var recipeAdapter = RecipeListAdapter(RecipeListener {
                recipeId ->
            // Navigate to the intersection view and provide the id of the intersection referenced
            // by the select RecyclerView item.
            this.findNavController().navigate(
                RecipeListFragmentDirections
                    .actionRecipeListFragmentToRecipeItemFragment(recipeId)
            )
        }, recipeViewModel)
        // Attach intersection adapter.
        binding.reciperecyclerview.adapter = recipeAdapter

        // Submit an updated list to the intersection listAdapter whenever it changes. Take note
        // intersectionList is a LiveData object.
        recipeViewModel.recipeList.observe(viewLifecycleOwner, Observer {
            it?.let {
                recipeAdapter.submitList(it)
            }
        })
        return binding.root
    }
}
//dasd