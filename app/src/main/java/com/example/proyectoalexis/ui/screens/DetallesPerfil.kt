package com.example.proyectoalexis.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoalexis.ui.navigation.Screens
import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesPerfil(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                /* AQUI VA EL CONTENIDO DEL MENU */
                Text(
                    "Menú",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(20.dp)
                )
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                NavigationDrawerItem(
                    label = { Text("Tareas") },
                    selected = false,
                    onClick = { navController.navigate(Screens.TableroPrincipal.route) },
                    icon = { Icon(Icons.Filled.Assignment, "") }
                )
                NavigationDrawerItem(
                    label = { Text("Equipos") },
                    selected = false,
                    onClick = { navController.navigate(Screens.Prueba.route) },
                    icon = { Icon(Icons.Filled.Contacts, "") }
                )
                NavigationDrawerItem(
                    label = { Text("Perfil") },
                    selected = true,
                    onClick = { /* ESTA EN ESA PANTALLA */ },
                    icon = { Icon(Icons.Filled.AccountCircle, "") }
                )
            }
        }
    )
    {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Detalles del perfil") },
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
                    }
                )

            }

        )
        { innerPading ->
            Box(modifier = Modifier.padding(innerPading))
            {
                Column(
                    modifier = Modifier.fillMaxSize().padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Icon(imageVector = Icons.Filled.AccountCircle, "",
                        Modifier.height(200.dp).width(200.dp).padding(bottom = 60.dp))
                    Card() {
                        Column(modifier = Modifier.padding(10.dp)) {

                        Text(
                            text = "Username: Texto de ejemplo", style = MaterialTheme.typography.titleLarge
                        )
                            Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
                        Text(
                            text = "Correo: Texto de ejemplo", style = MaterialTheme.typography.titleLarge
                        )
                            Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
                        Text(
                            text = "Contraseña: Texto de ejemplo", style = MaterialTheme.typography.titleLarge
                        )
                        }
                    }
                    Spacer(Modifier.height(60.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    )
                    {
                        Button(
                            onClick = { navController.navigate(Screens.EditarPerfil.route) },
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White,
                                disabledContainerColor = MaterialTheme.colorScheme.primary,
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text("Editar")
                        }
                        Button(
                            onClick = {},
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = Color.White,
                                disabledContainerColor = MaterialTheme.colorScheme.error,
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun Preview(){
    val navControllerLocal = rememberNavController()
    ProyectoALexisTheme{
        DetallesPerfil(navControllerLocal)
    }
}