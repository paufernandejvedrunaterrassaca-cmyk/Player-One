package com.victor.player1

import android.os.Bundle
import android.view.View
import com.victor.player1.databinding.FragmentHubBinding
class HubFragment : BindingFragment<FragmentHubBinding>(FragmentHubBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnWelcome.setOnClickListener { navigateTo(WelcomeFragment()) }
    }
}
