package com.victor.player1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.victor.player1.databinding.FragmentNewMisionBinding

class NewMisionFragment : BindingFragment<FragmentNewMisionBinding>(
    FragmentNewMisionBinding::inflate
) {

    private var misionEditar: Mision? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carregarMisionEditar()

        binding.btnSaveMission.setOnClickListener {
            guardarMision()
        }
    }

    private fun carregarMisionEditar() {
        val id = arguments?.getInt("mision_id", -1) ?: -1

        if (id != -1) {
            misionEditar = MisionsDataSource
                .obtenirMissions(requireContext())
                .firstOrNull { it.id == id && it.creadaPerUsuari }

            misionEditar?.let { mision ->
                binding.inputMissionName.setText(mision.titol)
                binding.inputMissionDescription.setText(mision.descripcio)
                binding.inputMissionReward.setText(mision.xp.toString())
            }
        }
    }

    private fun guardarMision() {
        val nom = binding.inputMissionName.text.toString().trim()
        val descripcio = binding.inputMissionDescription.text.toString().trim()
        val recompensa = binding.inputMissionReward.text.toString().trim().toIntOrNull()

        if (nom.isEmpty()) {
            binding.inputMissionName.error = binding.inputMissionName.hint
            return
        }

        if (recompensa == null || recompensa < 0) {
            binding.inputMissionReward.error = binding.inputMissionReward.hint
            return
        }

        val misions = MisionsDataSource.obtenirMissions(requireContext())

        if (misionEditar == null) {
            val novaMision = Mision(
                id = MisionsDataSource.obtenirSeguentId(requireContext()),
                emoji = "⭐",
                titol = nom,
                descripcio = descripcio,
                progres = 0,
                objectiu = 1,
                xp = recompensa,
                monedes = 0,
                estat = Estat.PENDENT,
                creadaPerUsuari = true
            )

            misions.add(novaMision)

            Log.d("NewMisionFragment", "Missió creada: $nom")

        } else {
            val index = misions.indexOfFirst { it.id == misionEditar!!.id }

            if (index != -1) {
                misions[index] = misionEditar!!.copy(
                    titol = nom,
                    descripcio = descripcio,
                    xp = recompensa
                )

                Log.d("NewMisionFragment", "Missió modificada: $nom")
            }
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.nova_missio_guardar),
            Toast.LENGTH_SHORT
        ).show()

        parentFragmentManager.popBackStack()
    }
}
