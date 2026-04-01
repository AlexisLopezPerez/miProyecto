package com.example.proyectoalexis.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoalexis.datos.EquiposDAO


class equipoViewModelFactory(private val dao: EquiposDAO, private val contexto: Context) : ViewModelProvider.Factory {

    // 2. función que Android llama cuando necesita el ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(equipoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return equipoViewModel(dao, contexto) as T
        }
        throw IllegalArgumentException("No se pudo crear este ViewModel")
    }
}
