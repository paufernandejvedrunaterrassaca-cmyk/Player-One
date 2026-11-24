package com.victor.player1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.victor.player1.databinding.FragmentLoginBinding

class LoginFragment : BindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.emailError.observe(viewLifecycleOwner) { error ->
            binding.editEmail.error = error
        }

        viewModel.passwordError.observe(viewLifecycleOwner) { error ->
            binding.editPassword.error = error
        }

        binding.buttonLogin.setOnClickListener {
            viewModel.setEmail(binding.editEmail.text.toString())
            viewModel.setPassword(binding.editPassword.text.toString())

            if (viewModel.validateLogin()) {
                Toast.makeText(requireContext(), "Dades correctes", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
