package com.example.sportshop.ui.data.mapper

import com.example.sportshop.ui.data.dto.AuthResponseDto
import com.example.sportshop.ui.domain.model.AuthData
import com.example.sportshop.ui.domain.model.AuthUser

object AuthMapper {

    fun  toDomain(authResponse: AuthResponseDto): AuthData
    {
        return AuthData(
            accessToken = authResponse.access_token,
            refreshToken = authResponse.refresh_token,
            user = AuthUser(
                id = authResponse.user.id,
                email = authResponse.user.email
            )
        )
    }
}