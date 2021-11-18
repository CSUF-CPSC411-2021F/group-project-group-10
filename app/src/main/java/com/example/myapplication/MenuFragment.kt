package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.databinding.FragmentMenuBinding
import com.google.firebase.auth.FirebaseAuth
import android.widget.TextView

import android.R
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController


class MenuFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMenuBinding.inflate(layoutInflater)

        Toast.makeText(context, "Welcome, ${FirebaseAuth.getInstance().currentUser?.displayName}!", Toast.LENGTH_LONG).show()

        // Click logic for logout (bottom right ImageView "button")
        // Navigates to the login fragment, and signs the user out.
        binding.logout.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(MenuFragmentDirections
                    .actionMenuFragmentToLogin())
            Toast.makeText(context, "Goodbye, ${FirebaseAuth.getInstance().currentUser?.displayName}.", Toast.LENGTH_LONG).show()
            auth.signOut()
        }

        return binding.root
    }
}