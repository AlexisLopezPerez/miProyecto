package com.example.proyectoalexis.datos

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.proyectoalexis.ui.screens.Equipos

@Entity(tableName = "tareas",
    foreignKeys = [
        ForeignKey(
            entity = Equipos::class,
            parentColumns = ["idEquipo"],
            childColumns = ["idEquipo"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Tareas(
    @PrimaryKey(autoGenerate = true) val idTarea: Int = 0,
    val nombre: String,
    val descripcion: String,
    val idEquipo: Int,          //llave foranea
)
