package com.example.sportshop.ui.domain.model

data class AuthData (
    val accessToken:String,
    val refreshToken:String,
    val user: AuthUser
)
