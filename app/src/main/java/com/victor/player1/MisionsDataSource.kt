package com.victor.player1

import android.content.Context

object MisionsDataSource {

    private var misions: MutableList<Mision>? = null

    fun obtenirMissions(context: Context): MutableList<Mision> {

        if (misions == null) {
            misions = mutableListOf(
                Mision(
                    1,
                    context.getString(R.string.missio_entrenament_emoji),
                    context.getString(R.string.missio_entrenament_titol),
                    context.getString(R.string.missio_entrenament_descripcio),
                    1,
                    3,
                    50,
                    100,
                    Estat.EN_PROGRES
                ),
                Mision(
                    2,
                    context.getString(R.string.missio_lectura_emoji),
                    context.getString(R.string.missio_lectura_titol),
                    context.getString(R.string.missio_lectura_descripcio),
                    12,
                    20,
                    50,
                    100,
                    Estat.EN_PROGRES
                ),
                Mision(
                    3,
                    context.getString(R.string.missio_habitacio_emoji),
                    context.getString(R.string.missio_habitacio_titol),
                    context.getString(R.string.missio_habitacio_descripcio),
                    0,
                    1,
                    50,
                    100,
                    Estat.PENDENT
                )
            )
        }

        return misions!!
    }

    fun obtenirMissionsCreades(context: Context): List<Mision> {
        return obtenirMissions(context).filter { it.creadaPerUsuari }
    }

    fun obtenirSeguentId(context: Context): Int {
        return (obtenirMissions(context).maxOfOrNull { it.id } ?: 0) + 1
    }

    fun obtenirMisionPerId(context: Context, id: Int): Mision? {
        return obtenirMissions(context).find { it.id == id }
    }

    fun afegirMision(context: Context, mision: Mision): Mision {
        val novaMision = mision.copy(
            id = obtenirSeguentId(context),
            creadaPerUsuari = true
        )

        obtenirMissions(context).add(novaMision)

        return novaMision
    }

    fun actualitzarMision(context: Context, mision: Mision): Boolean {
        val llista = obtenirMissions(context)
        val index = llista.indexOfFirst { it.id == mision.id }

        if (index == -1 || !llista[index].creadaPerUsuari) {
            return false
        }

        llista[index] = mision.copy(creadaPerUsuari = true)

        return true
    }

    fun eliminarMision(context: Context, id: Int): Boolean {
        val llista = obtenirMissions(context)
        val index = llista.indexOfFirst { it.id == id }

        if (index == -1 || !llista[index].creadaPerUsuari) {
            return false
        }

        llista.removeAt(index)

        return true
    }
}
