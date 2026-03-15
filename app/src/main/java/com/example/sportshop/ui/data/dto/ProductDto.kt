package com.example.sportshop.ui.data.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    val id: String,
    val title: String,
    val cost: Double,
    val description: String,
    @SerializedName("is_best_seller")
    val isBestSeller: Boolean?,
    val category: CategoryTitleDto?
)

data class CategoryTitleDto(
    val title: String
)