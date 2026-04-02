package com.example.proyectoalexis.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectoalexis.datos.AppDatabase
import com.example.proyectoalexis.datos.Equipos
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
import com.example.proyectoalexis.viewModel.equipoViewModel
import com.example.proyectoalexis.viewModel.equipoViewModelFactory

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(){
    //Paso 1 Crear el Nav Controller
    val navController = rememberNavController()

    val contexto = LocalContext.current

    val db = AppDatabase.getInstance(contexto)

    val factory = equipoViewModelFactory(db.equiposDAO(), contexto.applicationContext)

    val equipoViewModel: equipoViewModel = viewModel(factory = factory)

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
                        Equipos(
                            onLogin = {navController.navigate(Screens.Login.route)},
                            onDetallesPerfil = {navController.navigate(Screens.DetallesPerfil.route)},
                            onDetallesEquipo = {idEquipo ->
                                Log.d("AppNavigation, DetallesEquipo","ruta: editarEquipo/$idEquipo")
                                navController.navigate("detallesEquipo/$idEquipo")
                                               },
                            onTableroPrincipal = {navController.navigate(Screens.TableroPrincipal.route)},
                            onCrearEquipo = {navController.navigate(Screens.CrearEquipo.route)},
                            viewModel = equipoViewModel
                        )
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
                        DetallesPerfil(
                            onTableroPrincipal = {navController.navigate(Screens.TableroPrincipal.route)},
                            onEquipos = {navController.navigate(Screens.Equipos.route)},
                            onLogin = {navController.navigate(Screens.Login.route)},
                            onEditarPerfil = {navController.navigate(Screens.EditarPerfil.route)}
                        )
                    }

                    composable(
                        route = Screens.DetallesEquipo.route,
                        arguments = listOf(navArgument(name = "idEquipo") {type = NavType.IntType})
                    ){ backStackEntry ->
                        val idEquipo = backStackEntry.arguments?.getInt("idEquipo")?:0
                        val equipo = equipoViewModel.getEquipoById(idEquipo)

                        if(equipo != null) {

                            DetallesEquipo(
                                onEliminarEquipo = {equipoAEliminar ->
                                    equipoViewModel.eliminarEquipo(equipoAEliminar)
                                    navController.navigate(Screens.Equipos.route)
                                                   },
                                onGoBack = { navController.popBackStack() },
                                onEditarEquipo = {idEquipo ->
                                    navController.navigate("editarEquipo/$idEquipo")
                                },
                                equipo = equipo
                            )
                        }
                    }

                    composable(route = Screens.DetallesTarea.route){
                        DetallesTarea(
                            onGoBack = {navController.popBackStack()}
                        )
                    }

                    composable(route = Screens.EditarPerfil.route){
                        EditarPerfil(
                            onGoBack = {navController.popBackStack()},
                            onDetallesPerfil = {navController.navigate(Screens.DetallesPerfil.route)}
                        )
                    }

                    composable(
                        route = Screens.EditarEquipo.route,
                        arguments = listOf(navArgument(name = "idEquipo") {type = NavType.IntType})
                    ){ backStackEntry ->
                        val idEquipo = backStackEntry.arguments?.getInt("idEquipo")?:0
                        val equipo = equipoViewModel.getEquipoById(idEquipo)

                        if(equipo != null) {
                            EditarEquipo(
                                onGoBack = {navController.popBackStack()},
                                onActualizarEquipo = {equipoActualizado ->
                                    equipoViewModel.actualizarEquipo(equipoActualizado)
                                    navController.popBackStack()
                                                     },
                                equipo = equipo
                            )
                        }
                    }

                    composable(route = Screens.EditarTarea.route){
                        EditarTarea(
                            onGoBack = {navController.popBackStack()},
                            onDetallesTarea = {navController.navigate(Screens.DetallesTarea.route)}
                        )
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