package com.example.myapplication.ui.screens

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PantallaLogin(volverInicio: () -> Unit) {
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var mostrarErrores by remember { mutableStateOf(false) }
    var formularioValido by remember { mutableStateOf(false) }

    val correoValido = Patterns.EMAIL_ADDRESS
        .matcher(correo.trim())
        .matches()

    val claveValida = clave.length >= 6

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                formularioValido = false
            },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = mostrarErrores && !correoValido
        )

        if (mostrarErrores && !correoValido) {
            Text(
                text = "Ingresa un correo válido.",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = clave,
            onValueChange = {
                clave = it
                formularioValido = false
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = mostrarErrores && !claveValida
        )

        if (mostrarErrores && !claveValida) {
            Text(
                text = "La contraseña debe tener al menos 6 caracteres.",
                color = MaterialTheme.colorScheme.error
            )
        }

        if (formularioValido) {
            Text(
                text = "Los campos tienen un formato válido.",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                mostrarErrores = true
                formularioValido = correoValido && claveValida
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        TextButton(onClick = volverInicio) {
            Text("Volver al inicio")
        }
    }
}