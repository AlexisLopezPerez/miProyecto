package com.example.proyectoalexis.datos.datosIniciales

import android.content.Context
import com.example.proyectoalexis.datos.Usuarios

class DatosUsuarios(private val contexto: Context){
    fun loadUsuarios(): List<Usuarios>{
        return listOf<Usuarios>(
            Usuarios(
                nombre = "Alexis Lopez Perez",
                correo = "lpalexis@email.com",
                password = "1234"
            ),
            Usuarios(
                nombre = "Alejandra Mendez",
                correo = "malejandra@email.com",
                password = "1234"
            ),
            Usuarios(
                nombre = "Roberto Carlos Soto",
                correo = "csroberto@email.com",
                password = "1234"
            )
        )
    }
}
