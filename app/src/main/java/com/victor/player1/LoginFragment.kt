package com.victor.player1

import androidx.fragment.app.viewModels
import com.victor.player1.databinding.FragmentLoginBinding

class LoginFragment : BindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

}