package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentSetDisplayNameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class SetDisplayNameFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSetDisplayNameBinding.inflate(layoutInflater)

        binding.setName.setOnClickListener {

            var user_display_name: String = binding.displayName.text.toString()

            setDisplayName(user_display_name)
        }

        return binding.root
    }

    private fun setDisplayName(name: String) {

        if(TextUtils.isEmpty(name)) {
            Toast.makeText(context,"Input a moniker, please.", Toast.LENGTH_LONG).show()
        }

        // Value to store the changed data for display name.
        val updateInfo = userProfileChangeRequest {
            displayName = name
        }

        // Function call to facilitate the info change.
        auth.currentUser?.updateProfile(updateInfo)

        // Signs the user out.
        auth.signOut()

        requireView().findNavController()
            .navigate(SetDisplayNameFragmentDirections
                .actionSetDisplayNameFragmentToLogin())
    }
}