package com.example.myapplication.groceryItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.database.GroceryDatabase
import com.example.myapplication.databinding.GroceryItemFragmentBinding
/**
 * Fragment that displays a single intersection.
 */
class GroceryItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Retrieve arguments passed from the RecyclerView
        val args = GroceryItemFragmentArgs.fromBundle(
            requireArguments()
        )

        // Create data binding
        val binding: GroceryItemFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.grocery_item_fragment, container, false)

        // Get reference to this application
        val application = requireNotNull(this.activity).application

        // Retrieve Intersection data access object.
        val dataSource = GroceryDatabase.getInstance(application).groceryDao

        // Create a factory that generates an IntersectionViewModel connected to the database. The
        // groceryId passed from the RecyclerView is used to display the corresponding
        // information.
        val viewModelFactory =
            GroceryItemViewModelFactory(args.groceryId, dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val groceryItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(GroceryItemViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.groceryItemViewModel = groceryItemViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        return binding.root
    }
}