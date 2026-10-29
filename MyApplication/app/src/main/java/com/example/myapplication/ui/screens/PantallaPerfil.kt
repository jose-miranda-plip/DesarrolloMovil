package com.example.myapplication.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.myapplication.data.Usuario
import java.io.File
import java.io.FileOutputStream

@Composable
fun PantallaPerfil(
    usuario: Usuario,
    cerrarSesion: () -> Unit,
    volverInicio: () -> Unit,
    guardarFoto: (String) -> Unit
) {
    val context = LocalContext.current
    var mensajeCamara by remember { mutableStateOf<String?>(null) }

    val camara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            val uriFoto = guardarImagen(context, bitmap)
            guardarFoto(uriFoto)
            mensajeCamara = null
        } else {
            mensajeCamara = "No se tomó ninguna foto."
        }
    }

    val permisoCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            camara.launch(null)
        } else {
            mensajeCamara = "Se necesita permiso para usar la cámara."
        }
    }

    var mostrarDialogoCerrarSesion by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Mi perfil",
            style = MaterialTheme.typography.headlineMedium
        )

        if (usuario.fotoUri != null) {
            AsyncImage(
                model = usuario.fotoUri,
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Text("Nombre: ${usuario.nombre}")
        Text("Correo: ${usuario.email}")

        Button(
            onClick = {
                val permisoConcedido = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED

                if (permisoConcedido) {
                    camara.launch(null)
                } else {
                    permisoCamara.launch(Manifest.permission.CAMERA)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (usuario.fotoUri == null) "Tomar foto" else "Cambiar foto")
        }

        mensajeCamara?.let { mensaje ->
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = volverInicio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }

        Button(
            onClick = { mostrarDialogoCerrarSesion = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
        if (mostrarDialogoCerrarSesion) {
            AlertDialog(
                onDismissRequest = {
                    mostrarDialogoCerrarSesion = false
                },
                title = {
                    Text("Cerrar sesión")
                },
                text = {
                    Text("¿Seguro que quieres cerrar tu sesión?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            mostrarDialogoCerrarSesion = false
                            cerrarSesion()
                        }
                    ) {
                        Text("Sí, cerrar sesión")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            mostrarDialogoCerrarSesion = false
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }

    }
}

private fun guardarImagen(context: Context, bitmap: Bitmap): String {
    val archivo = File(
        context.filesDir,
        "perfil_${System.currentTimeMillis()}.jpg"
    )

    FileOutputStream(archivo).use { salida ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, salida)
    }

    return Uri.fromFile(archivo).toString()
}