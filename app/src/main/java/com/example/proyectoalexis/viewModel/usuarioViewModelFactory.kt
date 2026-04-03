package com.example.proyectoalexis.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoalexis.datos.UsuariosDAO

class usuarioViewModelFactory(private val dao: UsuariosDAO, private val contexto: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(usuarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return usuarioViewModel(dao, contexto) as T
        }
        throw IllegalArgumentException("No se pudo crear este ViewModel")
    }
}