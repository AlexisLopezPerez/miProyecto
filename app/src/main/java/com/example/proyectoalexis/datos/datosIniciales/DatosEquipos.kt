package com.example.proyectoalexis.datos.datosIniciales

import android.content.Context
import com.example.proyectoalexis.R
import com.example.proyectoalexis.datos.Equipos

class DatosEquipos(private val contexto: Context) {
    fun loadEquipos(): List<Equipos>{
        val packageName = contexto.packageName
        return listOf<Equipos>(
            Equipos(
                idEquipo = 1,
                nombre = "Empresarial",
                descripcion = "Equipo empresarial",
                imagenUri = "android.resource://$packageName/${R.drawable.hawaiana}"
            ),
            Equipos(
                idEquipo = 2,
                nombre = "Academico",
                descripcion = "Equipo Academico",
                imagenUri = "android.resource://$packageName/${R.drawable.pepperoni}"
            )
        )
    }
}