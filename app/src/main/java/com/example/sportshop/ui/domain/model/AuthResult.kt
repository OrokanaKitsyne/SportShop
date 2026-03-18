package com.example.sportshop.ui.domain.model

sealed class AuthResult<out T> {
    data class Success<out T>(val data: T) : AuthResult<T>()
    data class Failure(val message: String) : AuthResult<Nothing>()
}