package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

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
        val binding = FragmentLoginBinding.inflate(layoutInflater)

        // Attempts to log the user in by passing input
        // values to the authenticateLogin(...) function.
        binding.userLogin.setOnClickListener {

            var user_email: String = binding.username.text.toString()
            var user_password: String = binding.password.text.toString()

            authenticateLogin(user_email, user_password)
        }

        // Navigates to the register fragment if register textView is clicked.
        binding.register.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(LoginFragmentDirections
                    .actionLoginToRegisterFragment())
        }

        return binding.root
    }

    // Logic adopted and modified from https://gist.github.com/mishra3452/1dda2f91840a9b349dd79c7c4d05b1f0
    // Checks to see if the input credentials match a registered user in the Firebase.
    private fun authenticateLogin(email: String, password: String) {

            // Input validation to ensure data is obtained from both fields.
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(context,"Fill out both fields to continue.", Toast.LENGTH_LONG).show()
            }
             // If the input values match a registered user, they
             // are signed in and transferred to the menu fragment.
             else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(context, "Login successful!", Toast.LENGTH_LONG).show()
                        requireView().findNavController()
                            .navigate(LoginFragmentDirections
                                .actionLoginToMenuFragment())
                    }
                    // Login was unsuccessful due to incorrect user input
                    // or an unknown/deleted user registration.
                    else{
                        Toast.makeText(context, "Incorrect credentials.", Toast.LENGTH_LONG).show()

                    }
                })
            }
        }
    }
