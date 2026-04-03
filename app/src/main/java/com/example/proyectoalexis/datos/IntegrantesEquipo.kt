package com.example.proyectoalexis.datos

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "integrantesEquipo",
    foreignKeys = [
        ForeignKey(
            entity = Equipos::class,
            parentColumns = ["idEquipo"],
            childColumns = ["idEquipo"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Usuarios::class,
            parentColumns = ["idUsuario"],
            childColumns = ["idUsuario"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class IntegrantesEquipo(
    @PrimaryKey(autoGenerate = true) val idInnecesario: Int = 0,
    val idEquipo: Int,              //llave foranea
    val idUsuario: Int,             //llave foranea
)
