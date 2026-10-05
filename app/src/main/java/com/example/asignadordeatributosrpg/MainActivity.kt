package com.example.asignadordeatributosrpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.asignadordeatributosrpg.estado.RpgUiState
import com.example.asignadordeatributosrpg.ui.FilaAtributo
import com.example.asignadordeatributosrpg.ui.theme.AsignadorDeAtributosRPGTheme
import com.example.asignadordeatributosrpg.viewmodel.RpgViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsignadorDeAtributosRPGTheme {
                val viewModel: RpgViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaRpg(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun PantallaRpg(viewModel: RpgViewModel) {
    val estado: RpgUiState = viewModel.uiState.collectAsState(initial = RpgUiState()).value

    Column(modifier = Modifier.padding(16.dp)) {

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(estado.colorTematico)),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Clase: ${estado.clasePersonaje}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Text(
                    text = "Puntos Disponibles: ${estado.puntosDisponibles}",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }

        FilaAtributo(nombreAtributo = "Fuerza", valorActual = estado.fuerza) { nuevoValor ->
            viewModel.actualizarAtributo("Fuerza", nuevoValor)
        }
        FilaAtributo(nombreAtributo = "Magia", valorActual = estado.magia) { nuevoValor ->
            viewModel.actualizarAtributo("Magia", nuevoValor)
        }
        FilaAtributo(nombreAtributo = "Velocidad", valorActual = estado.velocidad) { nuevoValor ->
            viewModel.actualizarAtributo("Velocidad", nuevoValor)
        }
        FilaAtributo(nombreAtributo = "Defensa", valorActual = estado.defensa) { nuevoValor ->
            viewModel.actualizarAtributo("Defensa", nuevoValor)
        }
    }
}