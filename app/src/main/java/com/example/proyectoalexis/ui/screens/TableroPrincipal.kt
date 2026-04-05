package com.example.proyectoalexis.ui.screens

import android.content.Context
import android.util.Log.d
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.SheetState
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoalexis.R
import com.example.proyectoalexis.datos.Tareas
import com.example.proyectoalexis.datos.Usuarios
import com.example.proyectoalexis.ui.navigation.Screens
import com.example.proyectoalexis.viewModel.equipoViewModel
import com.example.proyectoalexis.viewModel.tareasViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableroPrincipal(
    onEquipos: () -> Unit,
    onDetallesPerfil: () -> Unit,
    onLogin: () -> Unit,
    onCrearTarea: () -> Unit,
    onDetallesTarea: () -> Unit,
    onEditarTarea: () -> Unit,
    tareasViewModel: tareasViewModel = viewModel(),
    equiposViewModel: equipoViewModel = viewModel()
) {

    val contexto = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val showDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val listaTareas by tareasViewModel.listaDeTareas.collectAsState(initial = emptyList())

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
                    onClick =  onEquipos ,
                    icon = {Icon(Icons.Filled.Contacts, "")}
                )
                NavigationDrawerItem(
                    label = { Text( "Perfil" ) },
                    selected = false,
                    onClick =  onDetallesPerfil ,
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
                onClick = onCrearTarea
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }

    )
    { innerPading ->

        Box(modifier = Modifier.padding(innerPading)) {
            LazyColumn (modifier = Modifier.fillMaxSize())
            {
                items(listaTareas){ tarea ->
                    TarjetaTarea(showBottomSheet, tarea, equiposViewModel,
                            onEditarTarea = onEditarTarea,
                        onDetallesTarea = onDetallesTarea,
                        sheetState = sheetState,
                        showDialog = showDialog,
                        contexto = contexto,
                        tareasViewModel = tareasViewModel
                        )
                }
            }
        }
        }
    }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TarjetaTarea(showBottomSheet: MutableState<Boolean>,
                     tarea: Tareas, equipoViewModel: equipoViewModel,
                     sheetState: SheetState,
                     onEditarTarea: () -> Unit,
                     onDetallesTarea: () -> Unit,
                     showDialog: MutableState<Boolean>,
                     contexto: Context,
                     tareasViewModel: tareasViewModel
    ) {
        var checked by remember() { mutableStateOf(false) }


            Card(
                modifier = Modifier.padding(10.dp),
                colors = CardDefaults.cardColors(
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            {

                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = tarea.nombre, style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.widthIn(min = 30.dp, max = 300.dp)
                        )
                        IconButton(
                            onClick = {
                                showBottomSheet.value = true
                                d("Boton Menu","Se esta intentando mostrar el menu")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = ""
                            )
                        }

                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = equipoViewModel.getNombreEquipoById(tarea.idEquipo),
                        modifier = Modifier.widthIn(min = 30.dp, max = 300.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = tarea.descripcion,
                            modifier = Modifier.widthIn(min = 30.dp, max = 300.dp)
                        )
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                    }
                }


            }
        if(showBottomSheet.value){
            MostrarMenu(
                sheetState = sheetState,
                showBottomSheet = showBottomSheet,
                onDetallesTarea = onDetallesTarea,
                onEditarTarea = onEditarTarea,
                showDialog = showDialog,
                tarea = tarea,
                contexto = contexto,
                tareasViewModel = tareasViewModel
            )
        }
    }

@Composable
private fun CuadroEliminar(showDialog: MutableState<Boolean>,
                           contexto: Context,
                           tareasViewModel: tareasViewModel,
                           tarea: Tareas
){
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Info, contentDescription = "Example Icon")
        },
        title = {
            Text(text = "Eliminar Tarea")
        },
        text = {
            Text(text = "¿Esta seguro que desea eliminar esta tarea de manera permanente?")
        },
        onDismissRequest = {
            showDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    showDialog.value = false
                    Toast.makeText(
                        contexto,
                        "La tarea se ha eliminado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    tareasViewModel.eliminarTarea(tarea)
                    /*  CODIGO QUE ELIMINA LA TAREA */
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MostrarMenu(
    sheetState: SheetState,
    showBottomSheet: MutableState<Boolean>,
    onDetallesTarea: () -> Unit,
    onEditarTarea: () -> Unit,
    showDialog: MutableState<Boolean>,
    contexto: Context,
    tareasViewModel: tareasViewModel,
    tarea: Tareas
){
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = { showBottomSheet.value = false }
    )
    {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            TextButton(
                onClick = onDetallesTarea
            )
            {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Info, "")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Detalles")
                }
            }
            TextButton(
                onClick = onEditarTarea
            )
            {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Create, "")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Editar")
                }
            }
            TextButton(
                onClick = {
                    showDialog.value = true
                }
            )
            {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Delete, "")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Eliminar")
                }
            }
            if (showDialog.value) {
                CuadroEliminar(showDialog,
                    contexto = contexto,
                    tarea = tarea,
                    tareasViewModel = tareasViewModel
                    )
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
        TableroPrincipal(navControllerLocal)
    }
}
*/