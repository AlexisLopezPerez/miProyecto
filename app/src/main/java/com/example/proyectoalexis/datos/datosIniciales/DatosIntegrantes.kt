package com.example.proyectoalexis.datos.datosIniciales

import com.example.proyectoalexis.datos.IntegrantesEquipo

class DatosIntegrantes() {
    fun loadIntegrantes(): List<IntegrantesEquipo>{
        return listOf<IntegrantesEquipo>(
            IntegrantesEquipo(
                idEquipo = 1,
                idUsuario = 1
            ),
            IntegrantesEquipo(
                idEquipo = 1,
                idUsuario = 2
            ),IntegrantesEquipo(
                idEquipo = 1,
                idUsuario = 3
            ),
            IntegrantesEquipo(
                idEquipo = 2,
                idUsuario = 1
            ),
            IntegrantesEquipo(
                idEquipo = 2,
                idUsuario = 2
            ),
            IntegrantesEquipo(
                idEquipo = 2,
                idUsuario = 3
            )
        )
    }
}