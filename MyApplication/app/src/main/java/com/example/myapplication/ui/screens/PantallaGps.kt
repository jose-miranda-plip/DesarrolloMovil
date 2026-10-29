package com.example.myapplication.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun PantallaGps(volverInformacion: () -> Unit) {
    val context = LocalContext.current
    val clienteUbicacion = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var ubicacion by remember { mutableStateOf<LatLng?>(null) }
    var mensaje by remember { mutableStateOf<String?>(null) }
    var cargando by remember { mutableStateOf(false) }

    val camara = rememberCameraPositionState {
        // Centro inicial del mapa; no representa la ubicación actual.
        position = CameraPosition.fromLatLngZoom(
            LatLng(-33.4489, -70.6693),
            12f
        )
    }

    fun tienePermisoUbicacion(): Boolean {
        val permisoPreciso = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val permisoAproximado = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return permisoPreciso || permisoAproximado
    }

    fun obtenerUbicacion() {
        if (!tienePermisoUbicacion()) {
            mensaje = "Debes conceder permiso de ubicación."
            return
        }

        cargando = true
        mensaje = null

        try {
            clienteUbicacion.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { location ->
                if (location != null) {
                    val nuevaUbicacion = LatLng(
                        location.latitude,
                        location.longitude
                    )
                    ubicacion = nuevaUbicacion
                    camara.position = CameraPosition.fromLatLngZoom(
                        nuevaUbicacion,
                        16f
                    )
                } else {
                    mensaje = "No se obtuvo la ubicación. Comprueba que el GPS esté activado."
                }

                cargando = false
            }.addOnFailureListener {
                mensaje = "No se pudo obtener la ubicación."
                cargando = false
            }
        } catch (e: SecurityException) {
            mensaje = "La aplicación no tiene permiso de ubicación."
            cargando = false
        }
    }

    val permisoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permisos ->
        val concedido =
            permisos[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permisos[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (concedido) {
            obtenerUbicacion()
        } else {
            mensaje = "Permiso denegado. No se puede mostrar tu ubicación."
        }
    }

    LaunchedEffect(Unit) {
        if (tienePermisoUbicacion()) {
            obtenerUbicacion()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(onClick = volverInformacion) {
            Text("← Volver a información")
        }

        Text(
            text = "Ubicación GPS",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = {
                if (tienePermisoUbicacion()) {
                    obtenerUbicacion()
                } else {
                    permisoLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            },
            enabled = !cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                when {
                    cargando -> "Obteniendo ubicación..."
                    tienePermisoUbicacion() -> "Actualizar ubicación"
                    else -> "Permitir y obtener ubicación"
                }
            )
        }

        mensaje?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        ubicacion?.let {
            Text("Latitud: ${it.latitude}")
            Text("Longitud: ${it.longitude}")
        }

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = camara
        ) {
            ubicacion?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Tu ubicación"
                )
            }
        }
    }
}