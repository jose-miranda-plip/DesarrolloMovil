package com.example.myapplication.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.ApiViewModel

@Composable
fun PantallaApi(
    volverInicio: () -> Unit,
    viewModel: ApiViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextButton(onClick = volverInicio) {
            Text("← Volver al inicio")
        }

        Text(
            text = "Pruebas de API",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Pulsa un botón para consultar un endpoint.")

        // Cada botón ejecuta una función distinta del ApiViewModel.
        Button(
            onClick = viewModel::cargarPublicaciones,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GET /posts")
        }

        Button(
            onClick = viewModel::cargarPublicacion,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GET /posts/1")
        }

        Button(
            onClick = viewModel::cargarComentarios,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GET /comments")
        }

        Button(
            onClick = viewModel::cargarAlbumes,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GET /albums")
        }

        Button(
            onClick = viewModel::cargarFotos,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GET /photos")
        }

        Button(
            onClick = viewModel::cargarTareas,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GET /todos")
        }

        Button(
            onClick = viewModel::enviarPublicacion,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("POST /posts")
        }

        if (viewModel.cargando) {
            CircularProgressIndicator()
        }

        viewModel.error?.let { mensaje ->
            Text(
                text = "Error: $mensaje",
                color = MaterialTheme.colorScheme.error
            )
        }

        if (viewModel.resultado.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = viewModel.resultado,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}