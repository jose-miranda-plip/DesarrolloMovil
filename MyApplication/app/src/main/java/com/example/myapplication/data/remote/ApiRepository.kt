package com.example.myapplication.data.remote

class ApiRepository(
    private val servicio: ApiService = ApiClient.servicio
) {
    suspend fun obtenerPublicaciones() =
        servicio.obtenerPublicaciones()

    suspend fun obtenerPublicacion(id: Int) =
        servicio.obtenerPublicacion(id)

    suspend fun obtenerComentarios() =
        servicio.obtenerComentarios()

    suspend fun obtenerAlbumes() =
        servicio.obtenerAlbumes()

    suspend fun obtenerFotos() =
        servicio.obtenerFotos()

    suspend fun obtenerTareas() =
        servicio.obtenerTareas()

    suspend fun crearPublicacion(publicacion: NuevaPublicacion) =
        servicio.crearPublicacion(publicacion)
}

