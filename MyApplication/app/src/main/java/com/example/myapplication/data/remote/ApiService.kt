package com.example.myapplication.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // GET 1: obtiene publicaciones.
    @GET("posts")
    suspend fun obtenerPublicaciones(): List<ApiPost>

    // GET 2: obtiene una publicación por su identificador.
    @GET("posts/{id}")
    suspend fun obtenerPublicacion(@Path("id") id: Int): ApiPost

    // GET 3: obtiene comentarios.
    @GET("comments")
    suspend fun obtenerComentarios(): List<ApiComment>

    // GET 4: obtiene álbumes.
    @GET("albums")
    suspend fun obtenerAlbumes(): List<ApiAlbum>

    // GET 5: obtiene fotos.
    @GET("photos")
    suspend fun obtenerFotos(): List<ApiPhoto>

    // GET 6: obtiene tareas.
    @GET("todos")
    suspend fun obtenerTareas(): List<ApiTodo>

    // POST 7: envía una publicación nueva.
    @POST("posts")
    suspend fun crearPublicacion(
        @Body publicacion: NuevaPublicacion
    ): ApiPost
}
