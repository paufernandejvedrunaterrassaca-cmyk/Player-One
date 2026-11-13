package com.victor.player1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.victor.player1.databinding.FragmentCreateMisionsBinding

class CreateMisionsFragment : BindingFragment<FragmentCreateMisionsBinding>(
    FragmentCreateMisionsBinding::inflate
) {

    private lateinit var adapter: MisionAdapter
    private lateinit var misions: MutableList<Mision>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecyclerView()
        configurarBotons()
    }

    private fun configurarRecyclerView() {
        misions = MisionsDataSource.obtenirMissions(requireContext())

        adapter = MisionAdapter(
            misions = misions,
            mostrarNomésCreades = true,

            onEdit = { mision ->
                val fragment = NewMisionFragment().apply {
                    arguments = Bundle().apply {
                        putInt("mision_id", mision.id)
                    }
                }
                navigateTo(fragment)
            },

            onDelete = { mision ->
                val posicio = misions
                    .filter { it.creadaPerUsuari }
                    .indexOfFirst { it.id == mision.id }

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
            Log.d(
                "CreateMisionsFragment",
                "Missió seleccionada: ${mision.titol}"
            )
        }

        binding.rvMissionsCreades.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvMissionsCreades.adapter = adapter
    }

    private fun configurarBotons() {
        binding.btnCreateMission.setOnClickListener {
            navigateTo(NewMisionFragment())
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        }

        (activity as? MainActivity)?.mostrarMenu(true)
    }
}
