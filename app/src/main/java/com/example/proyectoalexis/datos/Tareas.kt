package com.example.proyectoalexis.datos

data class Tareas(
    val idTarea: Int,
    val nombre: String,
    val descripcion: String,
    val equipoId: Int,          //llave foranea
)
