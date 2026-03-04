package com.example.proyectoalexis.ui.navigation

sealed class Screens (val route: String){
    object Login: Screens("login")
    object Registro: Screens("registro")
    object TableroPrincipal: Screens("tableroPrincipal")
    object CrearTarea: Screens("crearTarea")
    object DetallesTarea: Screens("detallesTarea")
    object EditarTarea: Screens("editarTarea")
    object DetallesPerfil: Screens("detallesPerfil")
    object EditarPerfil: Screens("editarPerfil")
    object Equipos: Screens("equipos")
    object CrearEquipo: Screens("crearEquipo")
    object DetallesEquipo: Screens("detallesEquipo")
    object EditarEquipo: Screens("editarEquipo")
    object Prueba: Screens("prueba")

}