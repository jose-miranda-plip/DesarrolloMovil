package com.example.myapplication.ui.registro

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.util.Validaciones

class RegistroViewModel(application: Application) : AndroidViewModel(application) {

        private val dao = AppDatabase.getDatabase(application).usuarioDao()

        var nombre by mutableStateOf("")
            private set
        var nombreError by mutableStateOf<String?>(null)
            private set

        fun onNombreChange(valor: String) {
            nombre = valor
            nombreError = Validaciones.validarNombre(valor)
        }
    }