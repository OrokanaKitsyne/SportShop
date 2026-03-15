package com.example.sportshop.ui.domain.usecase

import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthResult
import com.example.sportshop.ui.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email:String, password:String): AuthResult<AuthData>
    {
        return authRepository.login(email,password)
    }
}