package com.example.proyectoalexis.ui.navigation

import android.R
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoalexis.ui.screens.CrearEquipo
import com.example.proyectoalexis.ui.screens.CrearTarea
import com.example.proyectoalexis.ui.screens.DetallesEquipo
import com.example.proyectoalexis.ui.screens.DetallesPerfil
import com.example.proyectoalexis.ui.screens.DetallesTarea
import com.example.proyectoalexis.ui.screens.EditarEquipo
import com.example.proyectoalexis.ui.screens.EditarPerfil
import com.example.proyectoalexis.ui.screens.EditarTarea
import com.example.proyectoalexis.ui.screens.Equipos

import com.example.proyectoalexis.ui.screens.LoginScreen
import com.example.proyectoalexis.ui.screens.Prueba
import com.example.proyectoalexis.ui.screens.Registro
import com.example.proyectoalexis.ui.screens.TableroPrincipal

import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(){
    //Paso 1 Crear el Nav Controller
    val navController = rememberNavController()

        /*Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = { Text("Texto de ejemplo") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                    navigationIcon = { IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Flechita :)",
                            tint = Color.White
                        ) } }
                )

            }

        ) { innerPading ->*/
            Box(modifier = Modifier/*.padding(innerPading)*/){
                //Paso 2 Crear el Nav Host donde estaran todos los destinos
                NavHost(
                    navController = navController, startDestination = Screens.Login.route
                ){
                    //Paso 3 Declarar las rutas de navegacion
                    composable(route= Screens.Login.route){
                        LoginScreen(
                            onTableroPrincipal = {navController.navigate(Screens.TableroPrincipal.route)},
                            onRegistro = {navController.navigate(Screens.Registro.route)}
                        )
                    }

                    composable(route= Screens.Registro.route){
                        Registro(
                           onGoBack = {navController.popBackStack()},
                            onLogin = {navController.navigate(Screens.Login.route)}
                        )
                    }

                    composable(route = Screens.Equipos.route){
                        Equipos(navController)
                    }

                    composable(route = Screens.TableroPrincipal.route) {
                        TableroPrincipal(
                            onLogin = {navController.navigate(Screens.Login.route)},
                            onEquipos = {navController.navigate(Screens.Equipos.route)},
                            onCrearTarea = {navController.navigate(Screens.CrearTarea.route)},
                            onEditarTarea = {navController.navigate(Screens.EditarTarea.route)},
                            onDetallesTarea = {navController.navigate(Screens.DetallesTarea.route)},
                            onDetallesPerfil = {navController.navigate(Screens.DetallesPerfil.route)}
                        )
                    }

                    composable(route = Screens.DetallesPerfil.route){
                        DetallesPerfil(navController)
                    }

                    composable(route = Screens.DetallesEquipo.route){
                        DetallesEquipo(
                            onEquipos = {navController.navigate(Screens.Equipos.route)},
                            onGoBack = {navController.popBackStack()},
                            onTableroPrincipal = {navController.navigate(Screens.TableroPrincipal.route)},
                            onEditarEquipo = { navController.navigate(Screens.EditarEquipo.route) }
                        )
                    }

                    composable(route = Screens.DetallesTarea.route){
                        DetallesTarea(navController)
                    }

                    composable(route = Screens.EditarPerfil.route){
                        EditarPerfil(navController)
                    }

                    composable(route = Screens.EditarEquipo.route){
                        EditarEquipo(navController)
                    }

                    composable(route = Screens.EditarTarea.route){
                        EditarTarea(navController)
                    }

                    composable(route = Screens.CrearEquipo.route){
                        CrearEquipo(
                            onGoBack = {navController.popBackStack()}
                        )
                    }

                    composable(route = Screens.CrearTarea.route){
                        CrearTarea(
                            onGoBack = {navController.popBackStack()},
                            onTableroPrincipal = {navController.navigate(Screens.TableroPrincipal.route)}
                        )
                    }

                    composable(route = Screens.Prueba.route){
                        Prueba(navController)
                    }
                }
            }
        }



//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview(){
    ProyectoALexisTheme {
        AppNavigation()
    }

}