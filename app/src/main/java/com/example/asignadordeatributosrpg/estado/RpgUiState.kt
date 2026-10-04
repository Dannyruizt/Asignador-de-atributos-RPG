package com.example.asignadordeatributosrpg.estado

data class RpgUiState(
    val puntosDisponibles: Int = 100,
    val fuerza: Int = 0,
    val magia: Int = 0,
    val velocidad: Int = 0,
    val defensa: Int = 0,
    val clasePersonaje: String = "Novato",
    val colorTematico: Long = 0xe1dddd
)