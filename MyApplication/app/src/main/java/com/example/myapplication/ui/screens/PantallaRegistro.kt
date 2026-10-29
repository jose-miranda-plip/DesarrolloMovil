package com.example.myapplication.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.registro.RegistroViewModel

@Composable
fun PantallaRegistro(
    registroTerminado: () -> Unit,
    volverLogin: () -> Unit,
    viewModel: RegistroViewModel = viewModel()
) {
    var verClave by remember { mutableStateOf(false) }
    var verConfirmacion by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.registroExitoso) {
        if (viewModel.registroExitoso) registroTerminado()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crear cuenta",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoRegistro(
            valor = viewModel.nombre,
            onCambio = viewModel::onNombreChange,
            etiqueta = "Nombre",
            icono = { Icon(Icons.Default.Person, contentDescription = null) },
            error = viewModel.nombreError,
            teclado = KeyboardType.Text
        )

        CampoRegistro(
            valor = viewModel.email,
            onCambio = viewModel::onEmailChange,
            etiqueta = "Correo electrónico",
            icono = { Icon(Icons.Default.Email, contentDescription = null) },
            error = viewModel.emailError,
            teclado = KeyboardType.Email
        )

        CampoRegistro(
            valor = viewModel.password,
            onCambio = viewModel::onPasswordChange,
            etiqueta = "Contraseña",
            icono = { Icon(Icons.Default.Lock, contentDescription = null) },
            error = viewModel.passwordError,
            teclado = KeyboardType.Password,
            transformacion = if (verClave) VisualTransformation.None else PasswordVisualTransformation(),
            iconoFinal = {
                IconButton(onClick = { verClave = !verClave }) {
                    Icon(
                        imageVector = if (verClave) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (verClave) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            }
        )

        CampoRegistro(
            valor = viewModel.confirmacion,
            onCambio = viewModel::onConfirmacionChange,
            etiqueta = "Confirmar contraseña",
            icono = { Icon(Icons.Default.Lock, contentDescription = null) },
            error = viewModel.confirmacionError,
            teclado = KeyboardType.Password,
            transformacion = if (verConfirmacion) VisualTransformation.None else PasswordVisualTransformation(),
            iconoFinal = {
                IconButton(onClick = { verConfirmacion = !verConfirmacion }) {
                    Icon(
                        imageVector = if (verConfirmacion) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (verConfirmacion) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            }
        )

        MensajeError(viewModel.errorGeneral)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = viewModel::registrar,
            enabled = !viewModel.cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (viewModel.cargando) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Registrando...")
            } else {
                Text("Registrarme")
            }
        }

        TextButton(
            onClick = volverLogin,
            enabled = !viewModel.cargando,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}

@Composable
private fun CampoRegistro(
    valor: String,
    onCambio: (String) -> Unit,
    etiqueta: String,
    icono: @Composable () -> Unit,
    error: String?,
    teclado: KeyboardType,
    transformacion: VisualTransformation = VisualTransformation.None,
    iconoFinal: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta) },
        leadingIcon = icono,
        trailingIcon = iconoFinal,
        isError = error != null,
        singleLine = true,
        visualTransformation = transformacion,
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        modifier = Modifier.fillMaxWidth()
    )
    MensajeError(error)
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun MensajeError(mensaje: String?) {
    AnimatedVisibility(
        visible = mensaje != null,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Text(
            text = mensaje ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}