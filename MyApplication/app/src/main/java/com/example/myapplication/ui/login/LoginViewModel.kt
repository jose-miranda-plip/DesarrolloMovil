package com.example.myapplication.ui.login

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.Usuario
import com.example.myapplication.util.Seguridad
import com.example.myapplication.util.Validaciones
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).usuarioDao()

    var email by mutableStateOf("")
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var password by mutableStateOf("")
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var errorGeneral by mutableStateOf<String?>(null)
        private set

    var cargando by mutableStateOf(false)
        private set

    var usuarioAutenticado by mutableStateOf<Usuario?>(null)
        private set

    fun onEmailChange(valor: String) {
        email = valor
        emailError = Validaciones.validarEmail(valor.trim())
        errorGeneral = null
    }

    fun onPasswordChange(valor: String) {
        password = valor
        passwordError = Validaciones.validarPassword(valor)
        errorGeneral = null
    }

    fun iniciarSesion() {
        if (cargando) return

        emailError = Validaciones.validarEmail(email.trim())
        passwordError = Validaciones.validarPassword(password)

        if (emailError != null || passwordError != null) return

        viewModelScope.launch {
            cargando = true
            errorGeneral = null

            try {
                val correo = email.trim().lowercase()
                val usuario = dao.buscarPorEmail(correo)

                if (
                    usuario == null ||
                    usuario.passwordHash != Seguridad.hashPassword(password)
                ) {
                    errorGeneral = "Correo o contraseña incorrectos"
                } else {
                    usuarioAutenticado = usuario
                }
            } catch (e: Exception) {
                errorGeneral = "No se pudo iniciar sesión. Intenta nuevamente."
            } finally {
                cargando = false
            }
        }
    }

    fun cerrarSesion() {
        usuarioAutenticado = null
        password = ""
        errorGeneral = null
    }

    fun guardarFoto(uri: String) {
        val usuario = usuarioAutenticado ?: return

        viewModelScope.launch {
            try {
                dao.actualizarFoto(usuario.id, uri)

                // Actualiza también el estado que ve la pantalla Perfil.
                usuarioAutenticado = usuario.copy(fotoUri = uri)
            } catch (e: Exception) {
                errorGeneral = "No se pudo guardar la foto"
            }
        }
    }
}