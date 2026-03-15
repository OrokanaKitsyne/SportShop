package com.example.sportshop.ui.data.repository

import com.example.sportshop.ui.data.dto.LoginRequestDto
import com.example.sportshop.ui.data.dto.ProfileDto
import com.example.sportshop.ui.data.mapper.AuthMapper
import com.example.sportshop.ui.data.remote.AuthApi
import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthResult
import com.example.sportshop.ui.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryIml @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    companion object {
        var currentToken: String? = null
        var currentUserId: String? = null
    }

    override suspend fun login(email: String, password: String): AuthResult<AuthData> {
        return try {
            val response = authApi.login(LoginRequestDto(email, password))

            if (!response.isSuccessful) {
                return AuthResult.Failure("Ошибка входа: ${response.code()} ${response.message()}")
            }

            val body = response.body()
                ?: return AuthResult.Failure("Пустой ответ от сервера при входе")

            val authData = AuthMapper.toDomain(body)
            currentToken = authData.accessToken
            currentUserId = authData.user.id

            AuthResult.Success(authData)
        } catch (e: Exception) {
            AuthResult.Failure(e.message ?: "Ошибка входа")
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): AuthResult<AuthData> {
        return try {
            val registerResponse = authApi.register(
                mapOf(
                    "email" to email,
                    "password" to password
                )
            )

            if (!registerResponse.isSuccessful) {
                return AuthResult.Failure("Ошибка регистрации: ${registerResponse.code()} ${registerResponse.message()}")
            }

            val registerBody = registerResponse.body()
                ?: return AuthResult.Failure("Пустой ответ от сервера при регистрации")

            val authData = AuthMapper.toDomain(registerBody)
            val userId = authData.user.id

            val profileResponse = authApi.createProfile(
                ProfileDto(
                    user_id = userId,
                    firstname = name,
                    lastname = null,
                    address = null,
                    phone = null,
                    photo = null
                )
            )

            if (!profileResponse.isSuccessful) {
                return AuthResult.Failure(
                    "Аккаунт создан, но профиль не сохранён: ${profileResponse.code()} ${profileResponse.message()}"
                )
            }

            currentToken = authData.accessToken
            currentUserId = authData.user.id

            AuthResult.Success(authData)
        } catch (e: Exception) {
            AuthResult.Failure(e.message ?: "Ошибка регистрации")
        }
    }
}