package com.example.proyectoalexis.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoalexis.ui.navigation.Screens
import com.example.proyectoalexis.ui.theme.ProyectoALexisTheme

@Composable
fun Registro(navController: NavController) {
    var username by remember { mutableStateOf("")}
    var correo by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    var password2 by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text( "Nombre de usuario" ) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text( "Correo" ) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text( "Contraseña" ) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password2,
            onValueChange = { password2 = it },
            label = { Text( "Confirmar contraseña" ) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { navController.navigate(Screens.Login.route) }
        ) { Text( "Registrarse" ) }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            onClick = { navController.navigate(Screens.Login.route) },
        ) { Text(text = "Ya tengo una cuenta")}
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview(){
    val navControllerLocal = rememberNavController()
    ProyectoALexisTheme{
        Registro(navControllerLocal)
    }
}
