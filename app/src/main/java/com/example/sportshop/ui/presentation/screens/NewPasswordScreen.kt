package com.example.sportshop.ui.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NewPasswordScreen(navController: NavController) {

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier
                .align(Alignment.Start)
                .size(40.dp)
                .clickable { navController.popBackStack() },
            shape = CircleShape,
            tonalElevation = 2.dp
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Задать Новый Пароль",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Установите Новый Пароль Для Входа В\nВашу Учетную Запись",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text("Пароль", modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Default.VisibilityOff, null)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Подтверждение пароля", modifier = Modifier.align(Alignment.Start))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Default.VisibilityOff, null)
            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                navController.navigate(AppRoutes.LOGIN)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text("Сохранить")
        }
    }
}