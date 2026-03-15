package com.example.sportshop.ui.data.remote

<<<<<<< HEAD
import com.example.sportshop.ui.data.dto.AuthResponseDto
import com.example.sportshop.ui.data.dto.LoginRequestDto
import com.example.sportshop.ui.data.dto.ProfileDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/v1/token?grant_type=password")
    suspend fun login(
        @Body loginRequest: LoginRequestDto
    ): Response<AuthResponseDto>


    @POST("auth/v1/signup")
    suspend fun register(
        @Body body: Map<String, String>
    ): Response<AuthResponseDto>


    @Headers(
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @POST("rest/v1/profiles")
    suspend fun createProfile(
        @Body profile: ProfileDto
    ): Response<List<ProfileDto>>
=======
import com.example.exam3.data.dto.AuthResponseDto
import com.example.exam3.data.dto.LoginRequestDto
import dagger.Module
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface AuthApi {

@POST("auth/v1/token?grant_type=password")
suspend fun login(@Body loginRequest: LoginRequestDto):Response<AuthResponseDto>


>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
}