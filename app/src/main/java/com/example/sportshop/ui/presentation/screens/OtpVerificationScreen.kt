package com.example.sportshop.ui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OtpVerificationScreen(navController: NavController) {
    var otpValue by remember { mutableStateOf("") }

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
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Назад",
                modifier = Modifier.padding(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "OTP Проверка",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Пожалуйста, Проверьте Свою\nЭлектронную Почту, Чтобы Увидеть Код\nПодтверждения",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "OTP Код",
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = otpValue,
            onValueChange = { value ->

                if (value.length <= 6) {
                    otpValue = value
                }

                // как только введено 6 символов
                if (value.length == 6) {
                    navController.navigate(AppRoutes.NEW_PASSWORD)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    repeat(6) { index ->
                        val char = otpValue.getOrNull(index)?.toString() ?: ""
                        val isActive = index == otpValue.length

                        Box(
                            modifier = Modifier
                                .size(width = 38.dp, height = 70.dp)
                                .background(
                                    color = Color(0xFFF4F4F4),
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(
                                        Color.Transparent,
                                        RoundedCornerShape(12.dp)
                                    )
                            )

                            if (isActive) {
                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .background(
                                            Color.Transparent,
                                            RoundedCornerShape(12.dp)
                                        )
                                )
                            }

                            Text(
                                text = char,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "00:30",
            modifier = Modifier.align(Alignment.End),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}