package com.example.sportshop.ui.data.repository

import com.example.sportshop.ui.data.dto.LoginRequestDto
import com.example.sportshop.ui.data.dto.ProfileDto
import com.example.sportshop.ui.data.mapper.AuthMapper
import com.example.sportshop.ui.data.remote.AuthApi
import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthResult
import com.example.sportshop.ui.domain.model.AuthUser
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
            val cleanEmail = email.trim()
            val cleanPassword = password.trim()

            val response = authApi.login(
                LoginRequestDto(cleanEmail, cleanPassword)
            )

            if (!response.isSuccessful) {
                val errorText = response.errorBody()?.string()
                return AuthResult.Failure(
                    errorText ?: "Ошибка входа: ${response.code()} ${response.message()}"
                )
            }

            val body = response.body()
                ?: return AuthResult.Failure("Пустой ответ от сервера при входе")

            val authData = AuthMapper.toDomainOrNull(body)
                ?: return AuthResult.Failure("Сервер вернул неполные данные пользователя при входе")

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
            val cleanName = name.trim()
            val cleanEmail = email.trim()
            val cleanPassword = password.trim()

            val registerResponse = authApi.register(
                mapOf(
                    "email" to cleanEmail,
                    "password" to cleanPassword
                )
            )

            if (!registerResponse.isSuccessful) {
                val errorText = registerResponse.errorBody()?.string()
                return AuthResult.Failure(
                    errorText ?: "Ошибка регистрации: ${registerResponse.code()} ${registerResponse.message()}"
                )
            }

            val registerBody = registerResponse.body()
                ?: return AuthResult.Failure("Пустой ответ от сервера при регистрации")

            val userDto = registerBody.user
                ?: return AuthResult.Failure(
                    "Пользователь создан не полностью: сервер не вернул user.id"
                )

            val userId = userDto.id

            val profileResponse = authApi.createProfile(
                ProfileDto(
                    user_id = userId,
                    firstname = cleanName,
                    lastname = "",
                    address = "",
                    phone = "",
                    photo = null
                )
            )

            if (!profileResponse.isSuccessful) {
                val errorText = profileResponse.errorBody()?.string()
                return AuthResult.Failure(
                    errorText ?: "Аккаунт создан, но профиль не сохранён: ${profileResponse.code()} ${profileResponse.message()}"
                )
            }

            val authData = AuthData(
                accessToken = registerBody.access_token.orEmpty(),
                refreshToken = registerBody.refresh_token.orEmpty(),
                user = AuthUser(
                    id = userDto.id,
                    email = userDto.email
                )
            )

            currentToken = authData.accessToken
            currentUserId = authData.user.id

            AuthResult.Success(authData)
        } catch (e: Exception) {
            AuthResult.Failure(e.message ?: "Ошибка регистрации")
        }
    }
}