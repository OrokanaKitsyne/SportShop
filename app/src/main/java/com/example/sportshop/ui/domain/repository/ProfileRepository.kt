package com.example.sportshop.ui.domain.repository

import com.example.sportshop.ui.domain.model.ProfileData

interface ProfileRepository {
    suspend fun getProfile(userId: String): ProfileData?
    suspend fun saveProfile(profile: ProfileData): Result<Unit>
}