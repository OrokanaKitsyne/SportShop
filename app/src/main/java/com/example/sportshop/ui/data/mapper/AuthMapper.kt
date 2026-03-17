package com.example.sportshop.ui.data.mapper

import com.example.sportshop.ui.data.dto.AuthResponseDto
import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthUser

object AuthMapper {

    fun toDomainOrNull(authResponse: AuthResponseDto): AuthData? {
        val user = authResponse.user ?: return null
        val accessToken = authResponse.access_token ?: return null
        val refreshToken = authResponse.refresh_token ?: return null

        return AuthData(
            accessToken = accessToken,
            refreshToken = refreshToken,
            user = AuthUser(
                id = user.id,
                email = user.email
            )
        )
    }
}