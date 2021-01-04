package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        // Add listener for login button which will simply navigate to the welcome screen
        binding.loginButton.setOnClickListener { view: View -> view.findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToWelcome()
        ) }

        // Add listener for create account button which will simply navigate to the welcome screen
        binding.createAccountButton.setOnClickListener { view: View -> view.findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToWelcome()
        ) }

        return binding.root
    }

}