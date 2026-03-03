package com.example.proyectoalexis.ui.navigation

sealed class Screens (val route: String){
    object Login: Screens("login")
    object Registro: Screens("registro")
    object TableroPrincipal: Screens("tableroPrincipla")
}