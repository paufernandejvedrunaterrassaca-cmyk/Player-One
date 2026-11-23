package com.victor.player1

import androidx.fragment.app.viewModels
import com.victor.player1.databinding.FragmentLoginBinding

class LoginFragment : BindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: android.view.View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editEmail.setOnFocusChangeListener { _, _ -> viewModel.setEmail(binding.editEmail.text.toString())
        }

        binding.editPassword.setOnFocusChangeListener { _, _ -> viewModel.setPassword(binding.editPassword.text.toString())
        }
    }
}