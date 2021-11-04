package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentMenuBinding
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRegisterBinding.inflate(layoutInflater)

        // Create user onClickListener, allows registration
        // with appropriate user input credentials.
        binding.userReg.setOnClickListener {

            var user_email_reg: String = binding.emailReg.text.toString()
            var user_password_reg: String = binding.passwordReg.text.toString()

            authenticateRegister(user_email_reg, user_password_reg)
        }

        return binding.root
    }

    private fun authenticateRegister(email_reg: String, password_reg: String) {

        // Input validation to ensure data is obtained from both fields.
        if(TextUtils.isEmpty(email_reg) || TextUtils.isEmpty(password_reg)) {
            Toast.makeText(context,"Fill out both fields to continue.", Toast.LENGTH_LONG).show()
        }
        // Adds user to Firebase authentication if credentials are
        // within email format (something@somewhere.com), password
        // is at least 6 characters long, and the user isn't already
        // in the database.
        else {
            auth.createUserWithEmailAndPassword(email_reg, password_reg).addOnCompleteListener(requireActivity(), OnCompleteListener { task ->
                if(task.isSuccessful) {
                    Toast.makeText(context, "Registration successful! Proceed to login.", Toast.LENGTH_LONG).show()
                    requireView().findNavController()
                        .navigate(RegisterFragmentDirections
                            .actionRegisterFragmentToLogin())
                } else {
                    Toast.makeText(context, "Registration failed: \n" +
                            "Account may already exist. \n" +
                            "Email format: (something@somewhere.com). \n" +
                            "Password must be at least 6 characters long.", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}