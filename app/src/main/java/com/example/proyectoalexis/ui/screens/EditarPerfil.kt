package com.example.proyectoalexis.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoalexis.R
import com.example.proyectoalexis.ui.navigation.Screens
import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfil(
    onGoBack: () -> Unit,
    onDetallesPerfil: () -> Unit
) {

    val usernameString = stringResource(R.string.username)
    val passwordString = stringResource(R.string.password)
    val correoString = stringResource(R.string.correo)
    var username by remember { mutableStateOf(usernameString) }
    var correo by remember { mutableStateOf(correoString) }
    var password by remember { mutableStateOf(passwordString) }


        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Editar el perfil") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = onGoBack)
                        {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    Image(
                        painter = painterResource(R.drawable.pepperoni),
                        modifier = Modifier.height(200.dp).width(200.dp).padding(bottom = 60.dp),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Nombre de usuario") }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { username = it },
                        label = { Text("Correo") }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") }
                    )
                    Spacer(Modifier.height(60.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Button(
                            onClick = onDetallesPerfil,
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White,
                                disabledContainerColor = MaterialTheme.colorScheme.primary,
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text("Aceptar")
                        }

                    }
                }
            }
        }
    }

/*
@Preview(showBackground = true)
@Composable
private fun Preview(){
    val navControllerLocal = rememberNavController()
    ProyectoALexisTheme{
        EditarPerfil(navControllerLocal)
    }
}*/