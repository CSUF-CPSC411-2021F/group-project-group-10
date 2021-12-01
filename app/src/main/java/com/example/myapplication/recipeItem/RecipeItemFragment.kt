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

        // Retrieve Intersection data access object.
        val dataSource = RecipeDatabase.getInstance(application).recipeDao

        // Create a factory that generates an IntersectionViewModel connected to the database. The
        // intersectionId passed from the RecyclerView is used to display the corresponding
        // information.
        val viewModelFactory =
            RecipeItemViewModelFactory(args.recipeId, dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val recipeItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(RecipeItemViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.recipeItemViewModel = recipeItemViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        return binding.root
    }
}