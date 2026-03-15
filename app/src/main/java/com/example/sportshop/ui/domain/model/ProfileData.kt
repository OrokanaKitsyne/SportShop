package com.example.sportshop.ui.domain.model

data class ProfileData(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    val phone: String,
    val photoUrl: String?
) {
    val fullName: String
        get() = listOf(firstName, lastName)
            .filter { it.isNotBlank() }
            .joinToString(" ")
            .ifBlank { "Пользователь" }
}