package com.example.proyectoalexis.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoalexis.R
import com.example.proyectoalexis.ui.navigation.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Equipos(
    onTableroPrincipal: () -> Unit,
    onDetallesPerfil: () -> Unit,
    onLogin: () -> Unit,
    onCrearEquipo: () -> Unit,
    onDetallesEquipo: () -> Unit
){

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
                    selected = false,
                    onClick = onTableroPrincipal,
                    icon = {Icon(Icons.Filled.Assignment, "")}
                )
                NavigationDrawerItem(
                    label = { Text( "Equipos" ) },
                    selected = true,
                    onClick = { /* ESTA EN ESA PANTALLA */  },
                    icon = {Icon(Icons.Filled.Contacts, "")}
                )
                NavigationDrawerItem(
                    label = { Text( "Perfil" ) },
                    selected = false,
                    onClick = onDetallesPerfil,
                    icon = {Icon(Icons.Filled.AccountCircle, "")}
                )
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                NavigationDrawerItem(
                    label = { Text( "Cerrar Sesión" ) },
                    selected = false,
                    onClick = onLogin,
                    icon = {Icon(Icons.Filled.Logout, "")}
                )
            }
        }
    )
    {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Equipos") },
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
                    onClick = onCrearEquipo) {
                    Icon(Icons.Filled.Add, "")
                }
            }

        )
        { innerPading ->
            Box(modifier = Modifier.padding(innerPading))
            {
                /* CONTENIDO DE LA PANTALLA */
                Column() {
                    TarjetaEquipo(onDetallesEquipo)
                    TarjetaEquipo(onDetallesEquipo)
                    TarjetaEquipo(onDetallesEquipo)
                }

            }
    }
}
}

@Composable
fun TarjetaEquipo(
    onDetallesEquipo: () -> Unit
) {
    var checked by remember() { mutableStateOf(false) }
    var expanded by remember() { mutableStateOf(false) }

    TextButton(
        onClick = onDetallesEquipo,
        shape = RectangleShape
    )
    {
        Card(
            modifier = Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Image(painter = painterResource(R.drawable.hawaiana), "",
                Modifier.width(150.dp).height(150.dp),
                contentScale = ContentScale.Crop)
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = stringResource(R.string.nombreEquipo), style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(10.dp))
                Text(text = stringResource(R.string.descripcionEquipo))
                Spacer(Modifier.height(10.dp))
            }
        }



        }
    }

}
/*
@Preview(showBackground = true)
@Composable
private fun Preview(){
    val n = rememberNavController()
    Equipos(n)
}
*/