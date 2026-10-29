package com.example.myapplication.ui

import androidx.compose.runtime.*
import com.example.myapplication.ui.screens.PantallaInformacionMadera
import com.example.myapplication.ui.screens.PantallaInicio
import com.example.myapplication.ui.screens.PantallaLogin
import com.example.myapplication.ui.screens.PantallaGps

private enum class Pantalla {
    INICIO,
    MADERA,
    LOGIN,
    GPS
}

@Composable
fun AppContenido() {
    var pantallaActual by remember { mutableStateOf(Pantalla.INICIO) }

    when (pantallaActual) {
        Pantalla.INICIO -> PantallaInicio(
            abrirInformacion = { pantallaActual = Pantalla.MADERA },
            abrirLogin = { pantallaActual = Pantalla.LOGIN }
        )

        Pantalla.LOGIN -> PantallaLogin(
            volverInicio = { pantallaActual = Pantalla.INICIO }
        )

        Pantalla.MADERA -> PantallaInformacionMadera(
            volverInicio = { pantallaActual = Pantalla.INICIO },
            abrirGps = { pantallaActual = Pantalla.GPS }
        )

        Pantalla.GPS -> PantallaGps(
            volverInformacion = { pantallaActual = Pantalla.MADERA }
        )
    }
}