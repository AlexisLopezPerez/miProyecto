package com.example.proyectoalexis.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.proyectoalexis.ui.navigation.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableroPrincipal(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                /* AQUI VA EL CONTENIDO DEL MENU */
                Text("Menú", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(20.dp))
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                NavigationDrawerItem(
                    label = { Text( "Tareas" ) },
                    selected = true,
                    onClick = { /* ESTA EN ESA PANTALLA */ },
                    icon = {Icon(Icons.Filled.Assignment, "")}
                )
                NavigationDrawerItem(
                    label = { Text( "Equipos" ) },
                    selected = false,
                    onClick = { navController.navigate(Screens.DetallesEquipo.route) },
                    icon = {Icon(Icons.Filled.Contacts, "")}
                )
                NavigationDrawerItem(
                    label = { Text( "Perfil" ) },
                    selected = false,
                    onClick = { navController.navigate(Screens.DetallesPerfil.route) },
                    icon = {Icon(Icons.Filled.AccountCircle, "")}
                )
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                NavigationDrawerItem(
                    label = { Text( "Cerrar Sesión" ) },
                    selected = false,
                    onClick = { navController.navigate(Screens.Login.route) },
                    icon = {Icon(Icons.Filled.Logout, "")}
                )
            }
        }
    ) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tablero principal") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                    }
                    )
                    {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = "Flechita :)",
                            tint = Color.White
                        )
                    }
                },


            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.Prueba.route) }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }

    )
    { innerPading ->
        Box(modifier = Modifier.padding(innerPading)) {
            Column(modifier = Modifier.fillMaxSize())
            {
                TarjetaTarea(navController)
                TarjetaTarea(navController)
                TarjetaTarea(navController)
            }
        }
        }
    }
    }

    @Composable
    fun TarjetaTarea(navController: NavController) {
        var checked by remember() { mutableStateOf(false) }
        var expanded by remember() { mutableStateOf(false) }
        Card(
            modifier = Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {


            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Nombre de la tarea", style = MaterialTheme.typography.titleLarge)
                    IconButton(
                        onClick = { expanded = !expanded }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = ""
                        )
                    }

                }
                Spacer(Modifier.height(10.dp))
                Text("Equipos involucrados")
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Descripción")
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    leadingIcon = { Icon(imageVector = Icons.Filled.Create, "") },
                    text = { Text("Editar") },
                    onClick = { navController.navigate(Screens.Prueba.route) }
                )
                DropdownMenuItem(
                    leadingIcon = { Icon(imageVector = Icons.Filled.Delete, "") },
                    text = { Text("Eliminar") },
                    onClick = { navController.navigate(Screens.Prueba.route) }
                )
            }
        }
    }

@Preview(showBackground = true)
@Composable
private fun Preview(){
    val navControllerLocal = rememberNavController()
    ProyectoALexisTheme{
        TableroPrincipal(navControllerLocal)
    }
}