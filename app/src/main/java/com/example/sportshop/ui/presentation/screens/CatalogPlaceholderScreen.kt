package com.example.sportshop.ui.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CatalogPlaceholderScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Каталог товаров")
        Spacer(modifier = Modifier.height(12.dp))
        Text("Временный экран после входа.")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(AppRoutes.MAIN) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("На главный экран")
        }
    }
}