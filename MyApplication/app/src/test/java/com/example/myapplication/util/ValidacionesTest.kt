package com.example.myapplication.util

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class ValidacionesTest {

    @Test
    fun contraseñaValidaTieneOchoCaracteresYUnNumero() {
        val resultado = Validaciones.validarPassword("Clave123")

        assertNull(resultado)
    }

    @Test
    fun contraseñaCortaEsRechazada() {
        val resultado = Validaciones.validarPassword("abc123")

        assertNotNull(resultado)
    }

    @Test
    fun confirmacionIgualAContraseñaEsValida() {
        val resultado = Validaciones.validarConfirmacion(
            password = "Clave123",
            confirmacion = "Clave123"
        )

        assertNull(resultado)
    }

    @Test
    fun confirmacionDistintaAContraseñaEsRechazada() {
        val resultado = Validaciones.validarConfirmacion(
            password = "Clave123",
            confirmacion = "Clave456"
        )

        assertNotNull(resultado)
    }
}