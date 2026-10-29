package com.example.myapplication.ui.registro

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.Usuario
import com.example.myapplication.util.Seguridad
import com.example.myapplication.util.Validaciones
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistroViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).usuarioDao()

    var nombre by mutableStateOf("")
        private set
    var nombreError by mutableStateOf<String?>(null)
        private set

    var email by mutableStateOf("")
        private set
    var emailError by mutableStateOf<String?>(null)
        private set

    var password by mutableStateOf("")
        private set
    var passwordError by mutableStateOf<String?>(null)
        private set

    var confirmacion by mutableStateOf("")
        private set
    var confirmacionError by mutableStateOf<String?>(null)
        private set

    var errorGeneral by mutableStateOf<String?>(null)
        private set
    var cargando by mutableStateOf(false)
        private set
    var registroExitoso by mutableStateOf(false)
        private set

    fun onNombreChange(valor: String) {
        nombre = valor
        nombreError = Validaciones.validarNombre(valor)
        errorGeneral = null
    }

    fun onEmailChange(valor: String) {
        email = valor
        emailError = Validaciones.validarEmail(valor)
        errorGeneral = null
    }

    fun onPasswordChange(valor: String) {
        password = valor
        passwordError = Validaciones.validarPassword(valor)
        if (confirmacion.isNotEmpty()) {
            confirmacionError = Validaciones.validarConfirmacion(valor, confirmacion)
        }
        errorGeneral = null
    }

    fun onConfirmacionChange(valor: String) {
        confirmacion = valor
        confirmacionError = Validaciones.validarConfirmacion(password, valor)
        errorGeneral = null
    }

    fun registrar() {
        if (cargando) return

        nombreError = Validaciones.validarNombre(nombre)
        emailError = Validaciones.validarEmail(email)
        passwordError = Validaciones.validarPassword(password)
        confirmacionError = Validaciones.validarConfirmacion(password, confirmacion)

        if (listOf(nombreError, emailError, passwordError, confirmacionError).any { it != null }) {
            return
        }

        viewModelScope.launch {
            cargando = true
            errorGeneral = null
            delay(600)
            val correo = email.trim().lowercase()
            try {
                if (dao.buscarPorEmail(correo) != null) {
                    emailError = "Este correo ya está registrado"
                } else {
                    dao.insertar(
                        Usuario(
                            nombre = nombre.trim(),
                            email = correo,
                            passwordHash = Seguridad.hashPassword(password)
                        )
                    )
                    registroExitoso = true
                }
            } catch (e: SQLiteConstraintException) {
                emailError = "Este correo ya está registrado"
            } catch (e: Exception) {
                errorGeneral = "No se pudo completar el registro. Intenta nuevamente."
            } finally {
                cargando = false
            }
        }
    }
}