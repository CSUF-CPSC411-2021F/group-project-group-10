package com.example.myapplication.recipeItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.database.RecipeDatabase
import com.example.myapplication.databinding.RecipeInfoBinding


class RecipeItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve arguments passed from the RecyclerView
        val args = RecipeItemFragmentArgs.fromBundle(
            requireArguments()
        )

        // Create data binding
        val binding: RecipeInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.recipe_info, container, false)

        // Get reference to this application
        val application = requireNotNull(this.activity).application

        val dataSource = RecipeDatabase.getInstance(application).recipeDao

        val viewModelFactory =
            RecipeItemViewModelFactory(args.recipeId, dataSource, application)

        val recipeItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(RecipeItemViewModel::class.java)

        binding.recipeItemViewModel = recipeItemViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}