package com.example.myapplication.view

import androidx.compose.runtime.*
import com.example.myapplication.view.screens.PantallaInformacionMadera
import com.example.myapplication.view.screens.PantallaInicio
import com.example.myapplication.view.screens.PantallaLogin
import com.example.myapplication.view.screens.PantallaGps
import com.example.myapplication.view.screens.PantallaRegistro
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.LoginViewModel
import com.example.myapplication.view.screens.PantallaPerfil

private enum class Pantalla {
    INICIO,
    MADERA,
    LOGIN,
    REGISTRO,
    GPS,
    PERFIL
}

@Composable
fun AppContenido() {
    val loginViewModel: LoginViewModel = viewModel()
    var pantallaActual by remember { mutableStateOf(Pantalla.INICIO) }

    when (pantallaActual) {
        Pantalla.INICIO -> PantallaInicio(
            abrirInformacion = { pantallaActual = Pantalla.MADERA },
            abrirLogin = { pantallaActual = Pantalla.LOGIN }
        )

        Pantalla.LOGIN -> PantallaLogin(
            volverInicio = { pantallaActual = Pantalla.INICIO },
            abrirRegistro = { pantallaActual = Pantalla.REGISTRO },
            alIniciarSesion = { pantallaActual = Pantalla.PERFIL },
            viewModel = loginViewModel
        )

        Pantalla.REGISTRO -> PantallaRegistro(
            registroTerminado = { pantallaActual = Pantalla.LOGIN },
            volverLogin = { pantallaActual = Pantalla.LOGIN }
        )

        Pantalla.MADERA -> PantallaInformacionMadera(
            volverInicio = { pantallaActual = Pantalla.INICIO },
            abrirGps = { pantallaActual = Pantalla.GPS }
        )

        Pantalla.GPS -> PantallaGps(
            volverInformacion = { pantallaActual = Pantalla.MADERA }
        )

        Pantalla.PERFIL -> {
            val usuario = loginViewModel.usuarioAutenticado

            if (usuario != null) {
                PantallaPerfil(
                    usuario = usuario,
                    cerrarSesion = {
                        loginViewModel.cerrarSesion()
                        pantallaActual = Pantalla.INICIO
                    },
                    volverInicio = {
                        pantallaActual = Pantalla.INICIO
                    },
                    guardarFoto = { uri ->
                        loginViewModel.guardarFoto(uri)
                    }
                )
            }
        }
    }
}