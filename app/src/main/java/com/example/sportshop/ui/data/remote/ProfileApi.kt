package com.example.sportshop.ui.data.remote

import com.example.sportshop.ui.data.dto.ProfileDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Query

interface ProfileApi {

    @GET("rest/v1/profiles")
    suspend fun getProfileByUserId(
        @Query("select") select: String = "user_id,firstname,lastname,address,phone,photo",
        @Query("user_id") userId: String
    ): List<ProfileDto>

    @Headers(
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @PATCH("rest/v1/profiles")
    suspend fun updateProfile(
        @Query("user_id") userId: String,
        @Body profile: Map<String, Any?>
    ): Response<List<ProfileDto>>
}