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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sportshop.ui.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreeChecked by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val authState by viewModel.authState
    val loading by viewModel.loading
    val error by viewModel.error

    LaunchedEffect(authState) {
        if (authState != null) {
            navController.navigate(AppRoutes.MAIN) {
                popUpTo(AppRoutes.REGISTER) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.padding(start = 48.dp)
            ) {
                Text(
                    text = "Регистрация",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color(0xFF3C3C43)
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

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
                text = "Ваше имя",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF3C3C43)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            StyledField(
                value = name,
                onValueChange = { name = it },
                placeholder = "xxxxxxx",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Email",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF3C3C43)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            StyledField(
                value = email,
                onValueChange = { email = it },
                placeholder = "xyz@gmail.com",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.height(14.dp))

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
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = "Показать пароль",
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
                    cursorColor = Color(0xFF2D6D8C)
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .size(14.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            if (agreeChecked) Color(0xFF2D6D8C) else Color(0xFFF2F2F4)
                        )
                        .clickable { agreeChecked = !agreeChecked },
                    contentAlignment = Alignment.Center
                ) {
                    if (agreeChecked) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }

                Text(
                    text = "Даю согласие на обработку\nперсональных данных",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { agreeChecked = !agreeChecked },
                    style = TextStyle(
                        fontSize = 13.sp,
                        color = Color(0xFF7A7A80),
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (agreeChecked) {
                        viewModel.register(
                            firstName = name,
                            email = email,
                            password = password,
                            repeatPassword = password
                        )
                    }
                },
                enabled = !loading && agreeChecked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2F6F8E),
                    disabledContainerColor = Color(0xFF9FB8C6)
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
                        text = "Зарегистрироваться",
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
                    style = TextStyle(
                        fontSize = 13.sp,
                        color = Color.Red
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Есть аккаунт? ",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color(0xFF8C8C91)
                    )
                )

                Text(
                    text = "Войти",
                    modifier = Modifier.clickable {
                        navController.navigate(AppRoutes.LOGIN)
                    },
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color(0xFF3C3C43)
                    )
                )
            }
        }
    }
}

@Composable
private fun StyledField(
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
            cursorColor = Color(0xFF2D6D8C)
        )
    )
}