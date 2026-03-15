package com.example.sportshop.ui.data.repository

import com.example.sportshop.ui.data.remote.ProfileApi
import com.example.sportshop.ui.domain.model.ProfileData
import com.example.sportshop.ui.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileRepository {

    override suspend fun getProfile(userId: String): ProfileData? {
        val profile = profileApi.getProfileByUserId(
            userId = "eq.$userId"
        ).firstOrNull()

        return profile?.let {
            ProfileData(
                userId = it.user_id,
                firstName = it.firstname.orEmpty(),
                lastName = it.lastname.orEmpty(),
                address = it.address.orEmpty(),
                phone = it.phone.orEmpty(),
                photoUrl = it.photo
            )
        }
    }

    override suspend fun saveProfile(profile: ProfileData): Result<Unit> {
        return try {
            val response = profileApi.updateProfile(
                userId = "eq.${profile.userId}",
                profile = mapOf(
                    "firstname" to profile.firstName,
                    "lastname" to profile.lastName,
                    "address" to profile.address,
                    "phone" to profile.phone,
                    "photo" to profile.photoUrl
                )
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    IllegalStateException("Ошибка сохранения: ${response.code()} ${response.message()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}