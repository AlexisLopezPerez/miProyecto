package com.example.proyectoalexis.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoalexis.datos.TareasDAO

class tareasViewModelFactory(private val dao: TareasDAO, private val contexto: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(tareasViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return tareasViewModel(dao, contexto) as T
        }
        throw IllegalArgumentException("No se pudo crear este ViewModel (tareas)")
    }
}