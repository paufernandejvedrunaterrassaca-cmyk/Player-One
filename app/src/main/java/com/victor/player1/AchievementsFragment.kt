package com.victor.player1

import com.victor.player1.databinding.FragmentAchievementsBinding

class AchievementsFragment :
    BindingFragment<FragmentAchievementsBinding>(FragmentAchievementsBinding::inflate) {

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.mostrarMenu(true)
    }
}
