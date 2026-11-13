package com.victor.player1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.victor.player1.databinding.FragmentMissionsBinding

class MissionsFragment : BindingFragment<FragmentMissionsBinding>(
    FragmentMissionsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvMissions.layoutManager = LinearLayoutManager(requireContext())

        val misions = MisionsDataSource.obtenirMissions(requireContext())

        binding.rvMissions.adapter = MisionAdapter(misions) { mision ->
            Log.d("MissionsFragment", "Missió: ${mision.titol}")
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.mostrarMenu(true)
    }
}