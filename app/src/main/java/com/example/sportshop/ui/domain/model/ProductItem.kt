package com.example.sportshop.ui.domain.model

data class ProductItem(
    val id: String,
    val title: String,
    val cost: Double,
    val description: String,
    val isBestSeller: Boolean,
    val categoryTitle: String
)