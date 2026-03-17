package com.example.sportshop.ui.data.dto

data class ProfileUpdateDto(
    val firstname: String,
    val lastname: String,
    val address: String,
    val phone: String,
    val photo: String?
)