package com.example.sportshop.ui.presentation.viewmodel

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthResult
import com.example.sportshop.ui.domain.usecase.LoginUseCase
import com.example.sportshop.ui.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _authState = mutableStateOf<AuthData?>(null)
    val authState: State<AuthData?> = _authState

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun login(email: String, password: String) {
        val cleanEmail = email.trim()
        val cleanPassword = password.trim()

        if (cleanEmail.isBlank() || cleanPassword.isBlank()) {
            _error.value = "Введите email и пароль"
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches()) {
            _error.value = "Введите корректный email"
            return
        }

        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            when (val result = loginUseCase(cleanEmail, cleanPassword)) {
                is AuthResult.Success -> {
                    _authState.value = result.data
                    _error.value = null
                }

                is AuthResult.Failure -> {
                    _error.value = result.message
                    _authState.value = null
                }
            }

            _loading.value = false
        }
    }

    fun register(
        firstName: String,
        email: String,
        password: String,
        repeatPassword: String
    ) {
        val cleanFirstName = firstName.trim()
        val cleanEmail = email.trim()
        val cleanPassword = password.trim()
        val cleanRepeatPassword = repeatPassword.trim()

        if (
            cleanFirstName.isBlank() ||
            cleanEmail.isBlank() ||
            cleanPassword.isBlank() ||
            cleanRepeatPassword.isBlank()
        ) {
            _error.value = "Заполните все поля"
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches()) {
            _error.value = "Введите корректный email"
            return
        }

        if (cleanPassword != cleanRepeatPassword) {
            _error.value = "Пароли не совпадают"
            return
        }

        if (cleanPassword.length < 6) {
            _error.value = "Пароль должен быть не короче 6 символов"
            return
        }

        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            when (val result = registerUseCase(cleanFirstName, cleanEmail, cleanPassword)) {
                is AuthResult.Success -> {
                    _authState.value = result.data
                    _error.value = null
                }

                is AuthResult.Failure -> {
                    _error.value = result.message
                    _authState.value = null
                }
            }

            _loading.value = false
        }
    }
}