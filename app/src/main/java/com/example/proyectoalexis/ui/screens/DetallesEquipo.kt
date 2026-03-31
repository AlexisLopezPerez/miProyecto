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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoalexis.ui.navigation.Screens
import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme
import kotlinx.coroutines.launch
import com.example.proyectoalexis.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesEquipo(
    onGoBack: () -> Unit,
    onEditarEquipo: () -> Unit,
    onEquipos: () -> Unit,
) {

    val showDialog = remember { mutableStateOf(false) }
    val nombreEquipoString = stringResource(R.string.nombreEquipo)
    val descripcionEquipoString = stringResource(R.string.descripcionEquipo)


        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Detalles del equipo") },
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    item {
                        Image(
                            painter = painterResource(R.drawable.hawaiana),
                            contentDescription = "",
                            modifier = Modifier
                                .height(200.dp)
                                .width(200.dp)
                                .padding(bottom = 60.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    item {
                        Card() {
                            Column(modifier = Modifier.padding(10.dp)) {

                                Text(
                                    text = "Nombre del equipo:", style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = nombreEquipoString
                                )
                                Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
                                Text(
                                    text = "Descripción:", style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = descripcionEquipoString
                                )

                            }
                        }
                    }
                    item {
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        )
                        {
                            Button(
                                onClick = onEditarEquipo,
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
                                onClick =
                                    { showDialog.value = true }
                                ,
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
                    item {
                        Spacer(Modifier.height(20.dp))
                    }
                    item {
                        Card()
                        {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(
                                        text = "Integrantes:", style = MaterialTheme.typography.titleLarge
                                    )
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        Icon(imageVector = Icons.Filled.Add, "")
                                    }
                                }
                                Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Text(
                                        text = "Alexis"
                                    )
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        Icon(imageVector = Icons.Filled.Delete, "")
                                    }
                                }
                                Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Text(
                                        text = "Persona 1"
                                    )
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        Icon(imageVector = Icons.Filled.Delete, "")
                                    }
                                }
                                Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Text(
                                        text = "Persona 2"
                                    )
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        Icon(imageVector = Icons.Filled.Delete, "")
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Spacer(Modifier.height(60.dp))
                    }


                }
                if (showDialog.value) {
                    CuadroEliminar(onEquipos, showDialog)
                }
            }
        }
    }


@Composable
private fun CuadroEliminar(onEquipos: () -> Unit, showDialog: MutableState<Boolean>){
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Info, contentDescription = "Example Icon")
        },
        title = {
            Text(text = "Eliminar Equipo")
        },
        text = {
            Text(text = "¿Esta seguro que desea eliminar este equipo de manera permanente?")
        },
        onDismissRequest = {
            showDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = onEquipos) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showDialog.value = false
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}



/*
@Preview(showBackground = true)
@Composable
private fun Preview(){
    val navControllerLocal = rememberNavController()
    ProyectoALexisTheme{
        DetallesEquipo()
    }
}*/