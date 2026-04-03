package com.example.proyectoalexis.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalexis.datos.Usuarios
import com.example.proyectoalexis.datos.UsuariosDAO
import com.example.proyectoalexis.datos.datosIniciales.DatosUsuarios
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class usuarioViewModel(private val usuariosDAO: UsuariosDAO, private val contexto: Context): ViewModel() {
    val listaDeUsuarios = usuariosDAO.getAllUsuarios().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList<Usuarios>()
    )

    fun getIntegrantes(idEquipo: Int): Flow<List<Usuarios>> {
        return usuariosDAO.getUsuariosByIdEquipo(idEquipo)
    }

    fun getUsuarioById(idUsuario: Int): Usuarios?{
        return listaDeUsuarios.value.find { it.idUsuario == idUsuario }
    }

    fun getUsuarioByUsername(username: String): Usuarios?{
        return listaDeUsuarios.value.find { it.nombre == username }
    }

    fun comprobarContraseña(usuario: Usuarios, password: String): Boolean{
        if (usuario.password == password){
            return true
        }
        else{
            return false
        }
    }

    fun actualizarUsuario(usuarioActualizado: Usuarios){
        viewModelScope.launch {
            usuariosDAO.update(usuarioActualizado)
        }
    }

    fun eliminarUsuario(usuarioAEliminar: Usuarios){
        viewModelScope.launch {
            usuariosDAO.delete(usuarioAEliminar)
        }
    }

    fun crearUsuario(usuarioACrear: Usuarios){
        viewModelScope.launch {
            usuariosDAO.insert(usuarioACrear)
        }
    }

    init {
        viewModelScope.launch {
            val count = usuariosDAO.getCount()

            if(count == 0){
                val usuariosAInsertar = DatosUsuarios(contexto).loadUsuarios()

                usuariosAInsertar.forEach { usuario ->
                    usuariosDAO.insert(usuario)
                }
            }
        }
    }
}