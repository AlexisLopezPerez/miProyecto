package com.example.proyectoalexis.datos.datosIniciales

import com.example.proyectoalexis.datos.Tareas

class DatosTareas {
    fun loadTareas(): List<Tareas> {
        return listOf<Tareas>(
            Tareas(
                idTarea = 1,
                nombre = "Finalizar reporte de metricas mensuales",
                descripcion = "Revisar los datos de rendimiento del ultimo trimestre en Goolge Analytics y redactar las conclusiones principales",
                idEquipo = 1,
            ),
            Tareas(
                idTarea = 2,
                nombre = "Redaccion del marco metodologico",
                descripcion = "Estructurar la seccion correspondiente a la metodologia de la investigacion",
                idEquipo = 2,
            ),
            Tareas(
                idTarea = 3,
                nombre = "Revision de hitos del segundo trimestre (Q2)",
                descripcion = "Validar el cumplimiento de los indicadores clave de desempeño y documentar cualquier desviacion tecnica",
                idEquipo = 1,
            )
        )
    }
}