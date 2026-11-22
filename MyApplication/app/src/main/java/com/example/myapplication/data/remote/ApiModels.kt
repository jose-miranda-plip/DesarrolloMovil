package com.example.myapplication.data.remote

// Representa una publicación obtenida desde /posts.
data class ApiPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

// Representa un comentario obtenido desde /comments.
data class ApiComment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)

// Representa un álbum obtenido desde /albums.
data class ApiAlbum(
    val userId: Int,
    val id: Int,
    val title: String
)

// Representa una foto obtenida desde /photos.
data class ApiPhoto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

// Representa una tarea obtenida desde /todos.
data class ApiTodo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

// Datos que enviaremos al crear una publicación con POST.
data class NuevaPublicacion(
    val userId: Int,
    val title: String,
    val body: String
)