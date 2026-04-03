package com.example.proyectoalexis.datos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuarios(
    @PrimaryKey(autoGenerate = true) val idUsuario: Int = 0,
    val nombre: String,
    val correo: String,
    val password: String,
    val imagenUri: String
)
