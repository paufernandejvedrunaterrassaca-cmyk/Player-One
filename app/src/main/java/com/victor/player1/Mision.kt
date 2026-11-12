package com.victor.player1

enum class Estat {
    PENDENT,
    EN_PROGRES,
    COMPLETADA
}

data class Mision(
    val id: Int,
    val emoji: String,
    val titol: String,
    val descripcio: String,
    val progres: Int,
    val objectiu: Int,
    val xp: Int,
    val monedes: Int,
    val estat: Estat,
    val creadaPerUsuari: Boolean = false
)