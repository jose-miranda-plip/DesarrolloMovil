package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.ApiRepository
import com.example.myapplication.data.remote.NuevaPublicacion
import kotlinx.coroutines.launch


class ApiViewModel(
    private val repositorio: ApiRepository = ApiRepository()
) : ViewModel() {

    // Indica a la pantalla que hay una solicitud en curso.
    var cargando by mutableStateOf(false)
        private set

    // Texto que la pantalla mostrará cuando una solicitud falle.
    var error by mutableStateOf<String?>(null)
        private set

    // Resultado resumido de la última consulta.
    var resultado by mutableStateOf("")
        private set

    // GET 1: solicita la lista de publicaciones.
    fun cargarPublicaciones() = ejecutar("GET /posts") {
        repositorio.obtenerPublicaciones()
            .take(3)
            .joinToString(separator = "\n\n") {
                "Publicación ${it.id}: ${it.title}\n${it.body}"
            }
    }

    // GET 2: solicita una publicación específica.
    fun cargarPublicacion() = ejecutar("GET /posts/1") {
        val publicacion = repositorio.obtenerPublicacion(1)
        "Publicación ${publicacion.id}: ${publicacion.title}\n${publicacion.body}"
    }

    // GET 3: solicita algunos comentarios.
    fun cargarComentarios() = ejecutar("GET /comments") {
        repositorio.obtenerComentarios()
            .take(3)
            .joinToString(separator = "\n\n") {
                "${it.name} (${it.email})\n${it.body}"
            }
    }

    // GET 4: solicita algunos álbumes.
    fun cargarAlbumes() = ejecutar("GET /albums") {
        repositorio.obtenerAlbumes()
            .take(3)
            .joinToString(separator = "\n") {
                "Álbum ${it.id}: ${it.title}"
            }
    }

    // GET 5: solicita algunas fotos.
    fun cargarFotos() = ejecutar("GET /photos") {
        repositorio.obtenerFotos()
            .take(3)
            .joinToString(separator = "\n") {
                "Foto ${it.id}: ${it.title}"
            }
    }

    // GET 6: solicita algunas tareas.
    fun cargarTareas() = ejecutar("GET /todos") {
        repositorio.obtenerTareas()
            .take(3)
            .joinToString(separator = "\n") {
                val estado = if (it.completed) "completada" else "pendiente"
                "Tarea ${it.id} ($estado): ${it.title}"
            }
    }

    // POST 7: envía una publicación nueva a la API.
    fun enviarPublicacion() = ejecutar("POST /posts") {
        val respuesta = repositorio.crearPublicacion(
            NuevaPublicacion(
                userId = 1,
                title = "Publicación de prueba CENAMAD",
                body = "Contenido enviado desde la aplicación Android."
            )
        )

        "Respuesta del servidor:\n" +
                "ID: ${respuesta.id}\n" +
                "Título: ${respuesta.title}\n" +
                "Contenido: ${respuesta.body}"
    }

    private fun ejecutar(
        nombreEndpoint: String,
        solicitud: suspend () -> String
    ) {
        // Evita lanzar otra solicitud mientras una sigue activa.
        if (cargando) return

        viewModelScope.launch {
            cargando = true
            error = null
            resultado = "Consultando $nombreEndpoint..."

            try {
                resultado = solicitud()
            } catch (e: Exception) {
                error = e.message ?: "Ocurrió un error al conectar con la API."
                resultado = ""
            } finally {
                cargando = false
            }
        }
    }
}