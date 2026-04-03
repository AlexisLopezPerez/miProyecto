package com.example.proyectoalexis.datos.datosIniciales

import android.content.Context
import com.example.proyectoalexis.R
import com.example.proyectoalexis.datos.Usuarios

class DatosUsuarios(private val contexto: Context){
    fun loadUsuarios(): List<Usuarios>{
        val packageName = contexto.packageName
        return listOf<Usuarios>(
            Usuarios(
                nombre = "Alexis Lopez Perez",
                correo = "lpalexis@email.com",
                password = "1234",
                imagenUri = "android.resource://$packageName/${R.drawable.perfil_default}"
            ),
            Usuarios(
                nombre = "Alejandra Mendez",
                correo = "malejandra@email.com",
                password = "1234",
                imagenUri = "android.resource://$packageName/${R.drawable.perfil_default}"
            ),
            Usuarios(
                nombre = "Roberto Carlos Soto",
                correo = "csroberto@email.com",
                password = "1234",
                imagenUri = "android.resource://$packageName/${R.drawable.perfil_default}"
            )
        )
    }
}
