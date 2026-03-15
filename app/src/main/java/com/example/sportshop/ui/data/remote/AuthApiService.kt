package com.example.sportshop.ui.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthApiService {

    private const val BASE_URL = "https://zeivknuxlnrgxqcrppnl.supabase.co/"
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InplaXZrbnV4bG5yZ3hxY3JwcG5sIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzI2MTM5OTMsImV4cCI6MjA4ODE4OTk5M30.-3IJrvYP3dER_0fpP_eO6S6NsobRyostSxIs4rFns4Q"

    fun retrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("apikey", API_KEY)
                    .addHeader("Authorization", "Bearer $API_KEY")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun create(): AuthApi {
        return retrofit().create(AuthApi::class.java)
    }
}