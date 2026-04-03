package com.example.proyectoalexis.viewModel

import android.content.Context
import android.util.Log
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

    fun getIntegranteByIdEquipoAndIdUsuario(idEquipo: Int, idUsuario: Int): IntegrantesEquipo?{
        return listaDeIntegrantes.value.find {
            it.idUsuario == idUsuario && it.idEquipo == idEquipo
        }
    }

    fun eliminarIntegrante(integrantesEquipo: IntegrantesEquipo?){
        viewModelScope.launch {
            if (integrantesEquipo != null){ val integrante: IntegrantesEquipo = integrantesEquipo!!
                integrantesDAO.delete(integrante)
            }
            else{
                Log.d("integrantesViewModel (eliminarIntegrante)","No se elimino el integrante")
            }
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