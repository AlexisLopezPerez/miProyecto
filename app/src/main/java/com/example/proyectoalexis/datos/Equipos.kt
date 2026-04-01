package com.example.proyectoalexis.datos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipos")
data class Equipos(
    @PrimaryKey(autoGenerate = true) val idEquipo: Int = 0,
    val nombre: String,
    val descripcion: String,
    val imagenUri: String,
)
