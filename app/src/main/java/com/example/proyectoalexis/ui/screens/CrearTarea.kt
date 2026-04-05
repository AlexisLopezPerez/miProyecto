package com.example.proyectoalexis.ui.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.proyectoalexis.R
import com.example.proyectoalexis.datos.Equipos
import com.example.proyectoalexis.datos.IntegrantesEquipo
import com.example.proyectoalexis.datos.Tareas
import com.example.proyectoalexis.ui.navigation.Screens
import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme
import com.example.proyectoalexis.viewModel.equipoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearTarea(
    onGoBack: () -> Unit,
    onCrearTarea: (Tareas) -> Unit,
    equipoViewModel: equipoViewModel = viewModel()
) {

    var nombreTarea by remember { mutableStateOf("") }
    var descripcionTarea by remember { mutableStateOf("") }
    var nombreEquipo = remember { mutableStateOf("Asignar Equipo") }
    var idEquipo = remember { mutableStateOf(0) }

    val listaEquipos by equipoViewModel.listaDeEquipos.collectAsState(initial = emptyList())

    val contexto = LocalContext.current
    var showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Crear la tarea") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onGoBack

                    )
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                OutlinedTextField(
                    value = nombreTarea,
                    onValueChange = { nombreTarea = it },
                    label = { Text("Nombre de la tarea") }
                )
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(
                    value = descripcionTarea,
                    onValueChange = { descripcionTarea = it },
                    label = { Text("Descripción") }
                )
                Spacer(Modifier.height(20.dp))
                Card()
                {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            TextButton(
                                onClick = {
                                    Toast.makeText(
                                        contexto,
                                        "Funcion pendiente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AttachFile, ""
                                )
                                Text(
                                    text = "Agregar archivo"
                                )
                            }

                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                Card()
                {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            TextButton(
                                onClick = {
                                    showBottomSheet.value = true
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = nombreEquipo.value
                                )
                            }

                        }
                    }
                }
                Spacer(Modifier.height(60.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Button(
                        onClick = {
                            if (nombreTarea != "" && descripcionTarea != "" && idEquipo.value != 0)
                            {
                                val tareaACrear = Tareas(
                                    nombre = nombreTarea,
                                    descripcion = descripcionTarea,
                                    idEquipo = idEquipo.value
                                )
                                onCrearTarea(tareaACrear)
                                Toast.makeText(
                                    contexto,
                                    "La tarea se ha creado con exito",
                                    Toast.LENGTH_SHORT
                                )
                            }
                            else{
                                Toast.makeText(
                                    contexto,
                                    "Hay campos vacios, porfavor rellenelos",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                                  },
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
            if (showBottomSheet.value) {
                MostrarEquipos(
                    sheetState = sheetState,
                    showBottomSheet = showBottomSheet,
                    listaEquipos = listaEquipos,
                    idEquipo = idEquipo,
                    nombreEquipo = nombreEquipo
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarEquipos(showBottomSheet: MutableState<Boolean>, sheetState: SheetState,
                   listaEquipos: List<Equipos>,
                   idEquipo: MutableState<Int>,
                   nombreEquipo: MutableState<String>
                   ){
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = { showBottomSheet.value = false }
    )
    {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            items(listaEquipos) { equipo ->
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = equipo.imagenUri,
                            contentDescription = equipo.nombre,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp),
                            contentScale = ContentScale.Crop,
                            onError = { error ->
                                Log.e(
                                    "Detalles Equipo (MostrarUsuariosAgregar), ${equipo.nombre}",
                                    "Error al cargar ${error.result.throwable}"
                                )
                            }
                        )
                        Text(text = equipo.nombre, modifier = Modifier.padding(horizontal = 10.dp))
                    }
                    Button(
                        onClick = {
                            idEquipo.value = equipo.idEquipo
                            nombreEquipo.value = "Equipo Asignado: ${equipo.nombre}"
                            showBottomSheet.value = false
                        },
                        enabled = idEquipo.value != equipo.idEquipo
                    )
                    {
                        Icon(imageVector = Icons.Filled.Add, "")
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
        CrearTarea({},{})
    }
}*/