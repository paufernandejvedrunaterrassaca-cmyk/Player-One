package com.victor.player1

import com.victor.player1.databinding.FragmentGroupsBinding

class GroupsFragment :
    BindingFragment<FragmentGroupsBinding>(FragmentGroupsBinding::inflate) {

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.mostrarMenu(true)
        (activity as? MainActivity)?.seleccionarPestanya(R.id.nav_groups)
    }
}
