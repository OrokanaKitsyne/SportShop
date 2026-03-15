package com.example.sportshop.ui.domain.repository

<<<<<<< HEAD
import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthResult
=======
import com.example.exam3.domain.model.AuthData
import com.example.exam3.domain.model.AuthResult
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19

interface AuthRepository {

    suspend fun login(email:String, password:String): AuthResult<AuthData>
<<<<<<< HEAD
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): AuthResult<AuthData>
=======
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
}