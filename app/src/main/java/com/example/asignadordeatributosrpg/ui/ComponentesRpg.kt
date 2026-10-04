package com.example.asignadordeatributosrpg.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilaAtributo(
    nombreAtributo: String,
    valorActual: Int,
    onValorCambiado: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = nombreAtributo, style = MaterialTheme.typography.titleMedium)
            Text(text = valorActual.toString(), style = MaterialTheme.typography.titleMedium)
        }

        Slider(
            value = valorActual.toFloat(),
            onValueChange = { nuevoValorFlotante ->
                onValorCambiado(nuevoValorFlotante.toInt())
            },
            valueRange = 0f..100f
        )
    }
}