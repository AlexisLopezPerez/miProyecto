package com.example.proyectoalexis.ui.navigation

sealed class Screens (val route: String){
    data object Login: Screens("login")
    data object Registro: Screens("registro")
    data object TableroPrincipal: Screens("tableroPrincipal/{idUsuario}"){
        fun createRoute(idUsuario: Int) = "tableroPrincipal/$idUsuario"
    }
    data object CrearTarea: Screens("crearTarea")
    data object DetallesTarea: Screens("detallesTarea")
    data object EditarTarea: Screens("editarTarea")
    data object DetallesPerfil: Screens("detallesPerfil/{idUsuario}"){
        fun createRoute(idUsuario: Int) = "detallesPerfil/$idUsuario"
    }
    data object EditarPerfil: Screens("editarPerfil/{idUsuario}"){
        fun createRoute(idUsuario: Int) = "editarPerfil/$idUsuario"
    }
    data object Equipos: Screens("equipos/{idUsuario}"){
        fun createRoute(idUsuario: Int) = "equipos/$idUsuario"
    }
    data object CrearEquipo: Screens("crearEquipo")
    data object DetallesEquipo: Screens("detallesEquipo/{idEquipo}"){
        fun createRoute(idEquipo: Int) = "detallesEquipo/$idEquipo"
    }
    data object EditarEquipo: Screens("editarEquipo/{idEquipo}"){
        fun createRoute(idEquipo: Int) = "editarEquipo/$idEquipo"
    }
    data object Prueba: Screens("prueba")

}