package com.example.myapplication.util

import android.util.Patterns

object Validaciones {

    fun validarNombre(nombre: String): String? {
        return if (nombre.isBlank()) "Ingresa tu nombre" else null
    }

    fun validarEmail(email: String): String? {
        return when {
            email.isBlank() -> "Ingresa tu correo"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Correo no valido"
            else -> null
        }
    }

    fun validarPassword(password: String): String? {
        return when {
            password.length < 8 -> "Minimo 8 caracteres"
            !password.any { it.isDigit() } -> "Debe incluir al menos un numero"
            else -> null
        }
    }

    fun validarConfirmacion(password: String, confirmacion: String): String? {
        return if (password != confirmacion) "La contraseña no es correcta" else null
    }
}