package com.example.sportshop.ui.data.repository

import com.example.sportshop.ui.data.dto.ProfileUpdateDto
import com.example.sportshop.ui.data.remote.ProfileApi
import com.example.sportshop.ui.domain.model.ProfileData
import com.example.sportshop.ui.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileRepository {

    override suspend fun getProfile(userId: String): ProfileData? {
        val response = profileApi.getProfile(
            userId = "eq.$userId"
        )

        if (!response.isSuccessful) {
            return null
        }

        val profile = response.body()?.firstOrNull() ?: return null

        return ProfileData(
            userId = profile.user_id,
            firstName = profile.firstname.orEmpty(),
            lastName = profile.lastname.orEmpty(),
            address = profile.address.orEmpty(),
            phone = profile.phone.orEmpty(),
            photoUrl = profile.photo
        )
    }

    override suspend fun saveProfile(profile: ProfileData): Result<Unit> {
        return try {
            val response = profileApi.updateProfile(
                userId = "eq.${profile.userId}",
                profile = ProfileUpdateDto(
                    firstname = profile.firstName,
                    lastname = profile.lastName,
                    address = profile.address,
                    phone = profile.phone,
                    photo = profile.photoUrl
                )
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    IllegalStateException(
                        "Ошибка сохранения: ${response.code()} ${response.message()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}