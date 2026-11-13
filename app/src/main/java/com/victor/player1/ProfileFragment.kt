package com.victor.player1

import com.victor.player1.databinding.FragmentProfileBinding

class ProfileFragment :
    BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.mostrarMenu(true)
        (activity as? MainActivity)?.seleccionarPestanya(R.id.nav_profile)
    }
}
