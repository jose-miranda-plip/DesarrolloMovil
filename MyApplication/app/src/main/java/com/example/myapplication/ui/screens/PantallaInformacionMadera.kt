package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.TemaMadera

@Composable
fun PantallaInformacionMadera(
    volverInicio: () -> Unit,
    abrirGps:() -> Unit) {

    val temas = listOf(
        TemaMadera(
            "Propiedades de la madera",
            "Ficha de demostración sobre cómo se estudian las propiedades " +
                    "de la madera en proyectos de investigación."
        ),
        TemaMadera(
            "Madera en la construcción",
            "Tema de ejemplo relacionado con el uso de la madera en " +
                    "productos y soluciones para la construcción."
        ),
        TemaMadera(
            "Innovación y tecnología",
            "Ejemplo de cómo una investigación puede relacionarse con " +
                    "proyectos, publicaciones e infraestructura."
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(onClick = volverInicio) {
            Text("← Volver al inicio")
        }

        Text(
            text = "Información de madera",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text("Contenido de demostración para el MVP")

        temas.forEach { tema ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = tema.titulo,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(text = tema.descripcion)
                }
            }
        }

        Text(
            text = "Video informativo",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        VideoMadera()

        Button(
            onClick = abrirGps,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dónde encontrarlo (GPS)")
        }
    }
}