package com.example.asignadordeatributosrpg.viewmodel

import androidx.lifecycle.ViewModel
import com.example.asignadordeatributosrpg.estado.RpgUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RpgViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RpgUiState())
    val uiState: StateFlow<RpgUiState> = _uiState.asStateFlow()

    fun actualizarAtributo(atributo: String, nuevoValor: Int) {
        _uiState.update { estadoActual ->

            val valorAnterior = when (atributo) {
                "Fuerza" -> estadoActual.fuerza
                "Magia" -> estadoActual.magia
                "Velocidad" -> estadoActual.velocidad
                "Defensa" -> estadoActual.defensa
                else -> 0
            }

            // Calcular cuántos puntos está gastando o recuperando
            val diferencia = nuevoValor - valorAnterior

            if (estadoActual.puntosDisponibles - diferencia >= 0) {

                var nuevoEstado = estadoActual.copy(
                    puntosDisponibles = estadoActual.puntosDisponibles - diferencia
                )

                nuevoEstado = when (atributo) {
                    "Fuerza" -> nuevoEstado.copy(fuerza = nuevoValor)
                    "Magia" -> nuevoEstado.copy(magia = nuevoValor)
                    "Velocidad" -> nuevoEstado.copy(velocidad = nuevoValor)
                    "Defensa" -> nuevoEstado.copy(defensa = nuevoValor)
                    else -> nuevoEstado
                }

                return@update evaluarClasePersonaje(nuevoEstado)
            } else {
                return@update estadoActual
            }
        }
    }

    private fun evaluarClasePersonaje(estado: RpgUiState): RpgUiState {
        return when {
            estado.fuerza > 40 -> estado.copy(clasePersonaje = "Guerrero Implacable", colorTematico = 0xFFD32F2F)
            estado.magia > 40 -> estado.copy(clasePersonaje = "Hechicero Supremo", colorTematico = 0xFF512DA8)
            estado.velocidad > 40 -> estado.copy(clasePersonaje = "Asesino Veloz", colorTematico = 0xFF388E3C)
            estado.defensa > 40 -> estado.copy(clasePersonaje = "Paladín Escudo", colorTematico = 0xFF1976D2)
            estado.puntosDisponibles == 0 -> estado.copy(clasePersonaje = "Héroe Balanceado", colorTematico = 0xFFFBC02D)
            else -> estado.copy(clasePersonaje = "Novato", colorTematico = 0xFF9E9E9E)
        }
    }
}