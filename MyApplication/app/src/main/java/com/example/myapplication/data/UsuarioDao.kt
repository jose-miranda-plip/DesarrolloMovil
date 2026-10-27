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
}