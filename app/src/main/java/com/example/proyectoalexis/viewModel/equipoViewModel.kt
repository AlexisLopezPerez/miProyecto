package com.example.proyectoalexis.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalexis.datos.Equipos
import com.example.proyectoalexis.datos.EquiposDAO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


class equipoViewModel(private val equipoDAO: EquiposDAO, private val contexto: Context): ViewModel(){
    val listaDeEquipos = equipoDAO.getAllEquipos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList<Equipos>()
    )

    fun getEquipoById(idEquipo: Int): Equipos?{
    return listaDeEquipos.value.find { it.idEquipo == idEquipo }
    }
}