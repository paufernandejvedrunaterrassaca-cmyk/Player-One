package com.victor.player1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.victor.player1.databinding.FragmentMissionsBinding

class MissionsFragment : BindingFragment<FragmentMissionsBinding>(
    FragmentMissionsBinding::inflate
) {

    private lateinit var adapter: MisionAdapter
    private lateinit var misions: MutableList<Mision>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvMissions.layoutManager = LinearLayoutManager(requireContext())

        misions = MisionsDataSource.obtenirMissions(requireContext())

        adapter = MisionAdapter(
            misions = misions,

            onEdit = { mision ->
                val fragment = NewMisionFragment().apply {
                    arguments = Bundle().apply {
                        putInt("mision_id", mision.id)
                    }
                }
                navigateTo(fragment)
            },

            onDelete = { mision ->
                val posicio = misions.indexOfFirst {
                    it.id == mision.id
                }

                if (posicio != -1) {
                    adapter.removeAt(posicio)

                    Toast.makeText(
                        requireContext(),
                        getString(R.string.missio_eliminar) + ": " + mision.titol,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) { mision ->
            Log.d("MissionsFragment", "Missió: ${mision.titol}")
        }

        binding.rvMissions.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.mostrarMenu(true)
        (activity as? MainActivity)?.seleccionarPestanya(R.id.nav_missions)
    }
}
