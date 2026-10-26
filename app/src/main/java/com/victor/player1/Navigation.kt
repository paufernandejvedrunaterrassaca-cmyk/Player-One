package com.victor.player1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
fun Fragment.navigateTo(destination: Fragment) {
    parentFragmentManager.commit {
        setReorderingAllowed(true)
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        replace(R.id.fragment_container, destination)
        addToBackStack(null)
    }
}
