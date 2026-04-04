package com.example.proyectoalexis.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalexis.datos.Tareas
import com.example.proyectoalexis.datos.TareasDAO
import com.example.proyectoalexis.datos.datosIniciales.DatosTareas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class tareasViewModel(private val tareasDAO: TareasDAO, private val contexto: Context): ViewModel() {

    val listaDeTareas = tareasDAO.getAllTareas().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList<Tareas>()
    )

    fun getTareaById(idTarea: Int): Tareas?{
        return listaDeTareas.value.find { it.idTarea == idTarea }
    }

    fun actualizarTarea(tareas: Tareas){
        viewModelScope.launch {
            tareasDAO.update(tareas)
        }
    }

    fun eliminarTarea(tareas: Tareas){
        viewModelScope.launch {
            tareasDAO.delete(tareas)
        }
    }

    fun crearTarea(tareas: Tareas){
        viewModelScope.launch {
            tareasDAO.insert(tareas)
        }
    }

    init {
        viewModelScope.launch {
            val count = tareasDAO.getCount()
            if (count == 0){
                val tareasAInsertar = DatosTareas().loadTareas()

                tareasAInsertar.forEach { tarea ->
                    tareasDAO.insert(tarea)
                }
            }
        }
    }
}