package com.example.proyectoalexis.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.proyectoalexis.R
import com.example.proyectoalexis.datos.Equipos
import com.example.proyectoalexis.ui.navigation.Screens
import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearEquipo(
    onGoBack: () -> Unit,
    onEquipos: (Equipos) -> Unit
) {

    var nombreEquipo by remember { mutableStateOf("") }
    var descripcionEquipo by remember { mutableStateOf("") }
    var contexto = LocalContext.current
    var packageName = contexto.packageName
    var imagenDefaultUri by remember { mutableStateOf(Uri.parse("android.resource://$packageName/${R.drawable.equipo_default}")) }
    var galeryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null){
            try {
                //esto es para que android nos de permiso de acceder siempre a la imagen
                val contentResolver = contexto.contentResolver
                val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION

                //Hacer que el permiso persista
                contentResolver.takePersistableUriPermission(uri, takeFlags)
                imagenDefaultUri = uri
            }
            catch (e: Exception){
                imagenDefaultUri = uri
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Crear un equipo") },
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
                modifier = Modifier.fillMaxSize().padding(40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                AsyncImage(
                    model = imagenDefaultUri,
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .padding(bottom = 60.dp),
                    contentScale = ContentScale.Crop,
                    onError = { error ->
                        Log.e("Pantalla Crear Equipo","Error al cargar ${error.result.throwable}")
                    }
                )
                OutlinedButton(
                    onClick = {
                        galeryLauncher.launch("image/*")
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                )
                {
                    Text("Cambiar Foto")
                }
                OutlinedTextField(
                    value = nombreEquipo,
                    onValueChange = { nombreEquipo = it },
                    label = { Text("Nombre del equipo") }
                )
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    value = descripcionEquipo,
                    onValueChange = { descripcionEquipo = it },
                    label = { Text("Descripción") }
                )
                Spacer(Modifier.height(60.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Button(
                        onClick = {
                            if (nombreEquipo != "" && descripcionEquipo != "") {
                                val equipoACrear = Equipos(
                                    nombre = nombreEquipo,
                                    descripcion = descripcionEquipo,
                                    imagenUri = imagenDefaultUri.toString()
                                )

                                onEquipos(equipoACrear)

                                Toast.makeText(
                                    contexto,
                                    "El equipo fue creado exitosamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{
                                Toast.makeText(
                                    contexto,
                                    "Hay campos vacios. Porfavor, rellenelos",
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
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun Preview(){
    val navControllerLocal = rememberNavController()
    ProyectoALexisTheme{
        CrearEquipo({})
    }
}*/