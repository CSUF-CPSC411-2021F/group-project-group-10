package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProfileBinding.inflate(layoutInflater)

        // Changes the view text based off of the current user that's logged in.
        binding.emailFirebase.text = FirebaseAuth.getInstance().currentUser?.email
        binding.displayNameFirebase.text = FirebaseAuth.getInstance().currentUser?.displayName

        // onClick for user rename functionality.
        // Sends the user back to the display name fragment
        // for modification
        binding.changeName.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ProfileFragmentDirections
                    .actionProfileFragmentToSetDisplayNameFragment())
        }

        return binding.root
    }
}