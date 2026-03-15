package com.example.sportshop.ui.domain.model
<<<<<<< HEAD

sealed class AuthResult<out T> {
    data class Success<out T>(val data: T) : AuthResult<T>()
    data class Failure(val message: String) : AuthResult<Nothing>()
=======
import java.lang.Exception

sealed  class AuthResult<out T> {

    data class Success<out T>(val data:T): AuthResult<T>()
    data class Failure(val exception: Exception): AuthResult<Nothing>()


>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
}