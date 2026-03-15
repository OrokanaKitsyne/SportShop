package com.example.sportshop.ui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sportshop.ui.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState by viewModel.authState
    val loading by viewModel.loading
    val error by viewModel.error

    LaunchedEffect(authState) {
        if (authState != null) {
            navController.navigate(AppRoutes.MAIN) {
                popUpTo(AppRoutes.LOGIN) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF2F2F4))
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color(0xFF6F6F73),
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Привет!",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color(0xFF3C3C43)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Заполните Свои Данные",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFFA0A0A5)
                    )
                )
            }

            Spacer(modifier = Modifier.height(42.dp))

            Text(
                text = "Email",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF3C3C43)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            StyledLoginField(
                value = email,
                onValueChange = { email = it },
                placeholder = "xyz@gmail.com",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Пароль",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF3C3C43)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF3C3C43)
                ),
                placeholder = {
                    Text(
                        text = "••••••••",
                        color = Color(0xFFB8B8BE),
                        fontSize = 14.sp
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = "Скрытый пароль",
                            tint = Color(0xFF9A9AA0)
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F7),
                    unfocusedContainerColor = Color(0xFFF5F5F7),
                    disabledContainerColor = Color(0xFFF5F5F7),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color(0xFF3BA6E8)
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        navController.navigate(AppRoutes.FORGOT_PASSWORD)
                    }
                ) {
                    Text("Восстановить")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.login(email, password) },
                enabled = !loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF43A9E6),
                    disabledContainerColor = Color(0xFF9CCFEF)
                )
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Войти",
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    )
                }
            }

            if (!error.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = error ?: "",
                    color = Color.Red,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Вы впервые? ",
                    color = Color(0xFF8C8C91),
                    fontSize = 15.sp
                )

                Text(
                    text = "Создать",
                    modifier = Modifier.clickable {
                        navController.navigate(AppRoutes.REGISTER)
                    },
                    color = Color(0xFF3C3C43),
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
private fun StyledLoginField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Color(0xFF3C3C43)
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFB8B8BE),
                fontSize = 14.sp
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F7),
            unfocusedContainerColor = Color(0xFFF5F5F7),
            disabledContainerColor = Color(0xFFF5F5F7),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color(0xFF3BA6E8)
        )
    )
}