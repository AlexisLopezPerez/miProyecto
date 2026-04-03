package com.example.proyectoalexis.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalexis.datos.IntegrantesDAO
import com.example.proyectoalexis.datos.IntegrantesEquipo
import com.example.proyectoalexis.datos.Usuarios
import com.example.proyectoalexis.datos.datosIniciales.DatosIntegrantes
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class integrantesViewModel(private val integrantesDAO: IntegrantesDAO,
                           private val contexto: Context): ViewModel() {
    val listaDeIntegrantes = integrantesDAO.getAllIntegrantes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList<IntegrantesEquipo>()
    )

    fun getIntegranteById(idInnecesario: Int): IntegrantesEquipo?{
        return listaDeIntegrantes.value.find {
            it.idInnecesario == idInnecesario
        }
    }

    /*fun getIntegrantesByIdEquipo(idEquipo: Int): StateFlow<List<Usuarios>>{
        val listaIntegrantes = listaDeIntegrantes.value.filter {
            it.idEquipo == idEquipo
        }
        val listaUsuarios = flowOf(mutableListOf<Usuarios?>())
        listaIntegrantes.forEach { integrante ->
            listaUsuarios. /*add(integrantesDAO.getUsuarioById(integrante.idUsuario)*/
        }
        return listaUsuarios
    }*/

    fun eliminarIntegrante(integrantesEquipo: IntegrantesEquipo){
        viewModelScope.launch {
            integrantesDAO.delete(integrantesEquipo)
        }
    }

    fun agregarIntegrante(integrantesEquipo: IntegrantesEquipo){
        viewModelScope.launch {
            integrantesDAO.insert(integrantesEquipo)
        }
    }

    init {
        viewModelScope.launch {
            val count = integrantesDAO.getCount()

            if (count == 0){
                val integrantesAInsertar = DatosIntegrantes().loadIntegrantes()

                integrantesAInsertar.forEach { integrante ->
                    integrantesDAO.insert(integrante)
                }
            }
        }
    }
}