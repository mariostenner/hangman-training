package com.wizeline.academy.hangman.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.wizeline.academy.hangman.databinding.FragmentLoginBinding
import com.wizeline.academy.hangman.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.wizeline.academy.hangman.R
import androidx.navigation.Navigation
import androidx.navigation.navArgument

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        binding.button.setOnClickListener {
            val username = binding.etLogin.text
            if(!username.isNullOrEmpty()) {
                val bundleUsername = bundleOf("username" to binding.etLogin.text)
                navController.navigate(R.id.action_loginFragment_to_gameFragment, bundleUsername)
            }else{
                Toast.makeText(context,"Set username", Toast.LENGTH_LONG).show()
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}