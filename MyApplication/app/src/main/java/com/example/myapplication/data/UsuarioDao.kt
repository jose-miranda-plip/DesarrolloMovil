package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT * FROM Usuario WHERE email = :email LIMIT 1")
    suspend fun buscarPorEmail(email: String): Usuario?

    @Query("UPDATE Usuario SET fotoUri = :fotoUri WHERE id = :usuarioId")
    suspend fun actualizarFoto(usuarioId: Int, fotoUri: String)
}