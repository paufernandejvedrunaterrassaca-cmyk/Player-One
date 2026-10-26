package com.victor.player1

import android.view.View
import com.victor.player1.databinding.FragmentWelcomeBinding
class WelcomeFragment : BindingFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override val insetsTarget: View get() = binding.content
}
