package com.example.proyectoalexis.datos

data class ArchivosTarea(
    val idArchivo: Int,
    val nombre: String,
    val archivoUri: String,
    val idTarea: Int            //llave foranea
)
