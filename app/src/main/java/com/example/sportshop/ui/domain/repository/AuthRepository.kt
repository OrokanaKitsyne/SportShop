package com.example.sportshop.ui.domain.repository

import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthResult

interface AuthRepository {

    suspend fun login(email:String, password:String): AuthResult<AuthData>
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): AuthResult<AuthData>
}