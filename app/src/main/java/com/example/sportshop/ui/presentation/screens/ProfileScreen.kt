package com.example.sportshop.ui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sportshop.ui.data.repository.AuthRepositoryIml
import com.example.sportshop.ui.presentation.viewmodel.ProfileViewModel

private val ProfileBg = Color(0xFFF7F7F9)
private val FieldBg = Color(0xFFF1F1F4)
private val MainBlue = Color(0xFF47ACE8)
private val TextDark = Color(0xFF30343A)
private val TextHint = Color(0xFF9EA4AA)

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        val userId = viewModel.uiState.value.profile?.userId
            ?: AuthRepositoryIml.currentToken?.let { null }

        val currentUserId = viewModel.uiState.value.profile?.userId
        if (currentUserId == null) {
            // ВАЖНО:
            // сюда лучше подставить реальный userId после логина.
            // временно можно передать его из authState или сохранить в session manager.
        }
    }

    LaunchedEffect(state.saveSuccess) {
        if (state.saveSuccess) {
            snackBarHostState.showSnackbar("Изменения сохранены")
        }
    }

    val profileUserId = state.profile?.userId

    LaunchedEffect(profileUserId) {
        if (state.profile == null) {
            // ЗАМЕНИ на реальный userId текущего пользователя
            // например, если ты сохранишь его после логина:
            // viewModel.loadProfile(SessionManager.currentUserId)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ProfileBg)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MainBlue)
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    if (state.hasChanges) {
                        Button(
                            onClick = { viewModel.saveProfile() },
                            enabled = !state.isSaving,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MainBlue,
                                disabledContainerColor = Color(0xFFA8D9F4)
                            )
                        ) {
                            if (state.isSaving) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text("Сохранить", color = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))
                    } else {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = "Меню",
                                tint = TextDark
                            )
                        }

                        Text(
                            text = "Профиль",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            color = TextDark,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(MainBlue.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PersonOutline,
                                contentDescription = null,
                                tint = MainBlue,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(88.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFE2E6EA)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = (state.editedFirstName.firstOrNull()?.uppercase() ?: "U").toString(),
                                    color = TextDark,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = listOf(state.editedFirstName, state.editedLastName)
                                    .filter { it.isNotBlank() }
                                    .joinToString(" ")
                                    .ifBlank { "Пользователь" },
                                color = TextDark,
                                fontSize = 22.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Изменить фото профиля",
                                color = MainBlue,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    BarcodeStub()

                    Spacer(modifier = Modifier.height(20.dp))

                    ProfileField(
                        label = "Имя",
                        value = state.editedFirstName,
                        onValueChange = viewModel::onFirstNameChange
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    ProfileField(
                        label = "Фамилия",
                        value = state.editedLastName,
                        onValueChange = viewModel::onLastNameChange
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    ProfileField(
                        label = "Адрес",
                        value = state.editedAddress,
                        onValueChange = viewModel::onAddressChange
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    ProfileField(
                        label = "Телефон",
                        value = state.editedPhone,
                        onValueChange = viewModel::onPhoneChange
                    )

                    if (!state.error.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = state.error ?: "",
                            color = Color.Red,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    ProfileBottomBar(navController = navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp)
        )
    }
}

@Composable
private fun BarcodeStub() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val bars = listOf(
                18, 34, 26, 40, 22, 30, 16, 36, 24, 42, 20, 28, 18, 38, 24, 34, 16, 30, 22, 40
            )

            bars.forEachIndexed { index, height ->
                Box(
                    modifier = Modifier
                        .size(width = if (index % 3 == 0) 3.dp else 2.dp, height = height.dp)
                        .background(Color.Black)
                )
            }
        }
    }
}

@Composable
private fun ProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = label,
        fontSize = 15.sp,
        color = TextDark
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = TextDark
        ),
        trailingIcon = {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(MainBlue.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MainBlue,
                    modifier = Modifier.size(14.dp)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg,
            disabledContainerColor = FieldBg,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = MainBlue
        )
    )
}

@Composable
private fun ProfileBottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(Color.White)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "Главная",
            tint = TextHint,
            modifier = Modifier.clickable {
                navController.navigate(AppRoutes.MAIN)
            }
        )

        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Избранное",
            tint = TextHint
        )

        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(MainBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingBag,
                contentDescription = "Корзина",
                tint = Color.White
            )
        }

        Icon(
            imageVector = Icons.Outlined.NotificationsNone,
            contentDescription = "Уведомления",
            tint = TextHint
        )

        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .border(1.dp, MainBlue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonOutline,
                contentDescription = "Профиль",
                tint = MainBlue,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}