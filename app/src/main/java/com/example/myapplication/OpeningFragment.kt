package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentOpeningBinding

class OpeningFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Creates a binding for the opening fragment.
        val binding = FragmentOpeningBinding.inflate(layoutInflater)

        // On click listener for the Pho bowl on the opening screen.
        // Click it to proceed to the login fragment.
        binding.phoDistrict.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(OpeningFragmentDirections
                    .actionOpeningFragmentToLogin())

        }

        // Inflate the layout for this fragment
        return binding.root
    }
}