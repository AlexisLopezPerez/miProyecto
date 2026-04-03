package com.example.proyectoalexis.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyectoalexis.R
import com.example.proyectoalexis.datos.Equipos
import com.example.proyectoalexis.datos.Usuarios
import com.example.proyectoalexis.viewModel.integrantesViewModel
import com.example.proyectoalexis.viewModel.usuarioViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesEquipo(
    onGoBack: () -> Unit,
    onEditarEquipo: (Int) -> Unit,
    onEliminarEquipo: (Equipos) -> Unit,
    viewModel: usuarioViewModel = viewModel(),
    integrantesViewModel: integrantesViewModel = viewModel(),
    equipo: Equipos
) {

    val showDialog = remember { mutableStateOf(false) }
    val showDialogListaIntegrantes = remember { mutableStateOf(false) }
    var showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val nombreEquipoString = equipo.nombre
    val descripcionEquipoString = equipo.descripcion
    val currentImageUri = Uri.parse(equipo.imagenUri)
    val listaIntegrantes by viewModel.getIntegrantes(equipo.idEquipo).collectAsStateWithLifecycle(initialValue = emptyList())
    val listaUsuarios by viewModel.listaDeUsuarios.collectAsState(initial = emptyList())
    //val listaIntegrantes by viewModel.listaDeUsuarios.collectAsState(initial = emptyList())

    //viewModel.listaDeUsuarios.collectAsState(initial = emptyList())
    Log.d("Detalles Equipo (main)", "Tamaño de Lista Integrantes: ${listaIntegrantes.size}")
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
                /*LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    item {*/
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    AsyncImage(
                        model = currentImageUri,
                        contentDescription = equipo?.nombre,
                        modifier = Modifier
                            .height(200.dp)
                            .width(200.dp)
                            .padding(bottom = 60.dp),
                        contentScale = ContentScale.Crop,
                        onError = { error ->
                            Log.e(
                                "Pantalla Detalles Equipo, ${equipo?.nombre}",
                                "Error al cargar ${error.result.throwable}"
                            )
                        }
                    )



                    Card() {
                        Column(modifier = Modifier.padding(10.dp)) {

                            Text(
                                text = "Nombre del equipo:",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = nombreEquipoString ?: ""
                            )
                            Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
                            Text(
                                text = "Descripción:", style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = descripcionEquipoString ?: ""
                            )

                        }
                    }


                        Spacer(Modifier.height(20.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        )
                        {
                            Button(
                                onClick = { onEditarEquipo(equipo.idEquipo) },
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
                                    { showDialog.value = true },
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
                        //}
                        //item {
                        Spacer(Modifier.height(20.dp))
                        Card() {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Integrantes:",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                IconButton(
                                    onClick = {
                                        showBottomSheet.value = true
                                    }
                                ) {
                                    Icon(imageVector = Icons.Filled.Add, "")
                                }
                            }
                            escribirIntegrantes(
                                listaIntegrantes,
                                viewModel,
                                showDialogListaIntegrantes
                            )
                        }
                    }
                    }


                    // }
                    //item {

                    // } }
                    if(showBottomSheet.value){
                        MostrarUsuariosAgregar(showBottomSheet, sheetState, listaUsuarios, listaIntegrantes)
                    }

                }

                if (showDialog.value) {
                    CuadroEliminar(onEliminarEquipo, showDialog, equipo)
                }

                if (showDialogListaIntegrantes.value) {
                    CuadroEliminarIntegrante(showDialogListaIntegrantes = showDialogListaIntegrantes)

                }
            }

    }


@Composable
private fun CuadroEliminar(onEliminarEquipo: (Equipos) -> Unit, showDialog: MutableState<Boolean>, equipo: Equipos){
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
                onClick = {
                    onEliminarEquipo(equipo)
                }
            ) {
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

@Composable
private fun CuadroEliminarIntegrante(showDialogListaIntegrantes: MutableState<Boolean>){
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Info, contentDescription = "Example Icon")
        },
        title = {
            Text(text = "Eliminar Integrante")
        },
        text = {
            Text(text = "¿Esta seguro que desea eliminar este integrante de este equipo?")
        },
        onDismissRequest = {
            showDialogListaIntegrantes.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    showDialogListaIntegrantes.value = false
                }
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showDialogListaIntegrantes.value = false
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun escribirIntegrantes(listaIntegrantes: List<Usuarios>,
                        viewModel: usuarioViewModel = viewModel(),
                        showDialogListaIntegrantes: MutableState<Boolean>
){
    Log.d("Detalles Equipo (escribirIntegrantes)", "Tamaño de Lista Integrantes: ${listaIntegrantes.size}")
    LazyColumn()
    {
        items(listaIntegrantes) { integrante ->
            Divider(Modifier.padding(vertical = 10.dp), color = Color.LightGray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = integrante.nombre
                )
                IconButton(
                    onClick = {
                        //CAMBIAR POR UN CUADRO DE SELECCION
                        showDialogListaIntegrantes.value = true
                        //viewModel.eliminarUsuario(integrante)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Delete, "")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarUsuariosAgregar(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    listaUsuarios: List<Usuarios>,
    listaIntegrantes: List<Usuarios>
)
{
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = { showBottomSheet.value = false }
    )
    {
        LazyColumn(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            items(listaUsuarios) { usuario ->

                Row(
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = usuario.imagenUri,
                            contentDescription = usuario.nombre,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp),
                            contentScale = ContentScale.Crop,
                            onError = { error ->
                                Log.e(
                                    "Pantalla Detalles Equipo (MostrarUsuariosAgregar), ${usuario.nombre}",
                                    "Error al cargar ${error.result.throwable}"
                                )
                            }
                        )
                        Text(text = usuario.nombre, modifier = Modifier.padding(horizontal = 10.dp))
                    }
                    Button(
                        onClick = {
                            showBottomSheet.value = false
                        },
                        enabled = !(EstaEsteUsaurioEnEsteEquipo(usuario.idUsuario, listaIntegrantes))
                    )
                    {
                        Icon(imageVector = Icons.Filled.Add, "")
                    }
                }
            }
        }
    }
}

fun EstaEsteUsaurioEnEsteEquipo(idUsuario: Int, listaIntegrantes: List<Usuarios>): Boolean{
    var result = false
    listaIntegrantes.forEach { integrante ->
        if (integrante.idUsuario == idUsuario) result = true
    }
    return result
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