package com.example.sportshop.ui.domain.repository

import com.example.sportshop.ui.domain.model.CategoryItem
import com.example.sportshop.ui.domain.model.ProductItem

interface ProductRepository {
    suspend fun getCategories(): List<CategoryItem>
    suspend fun getProducts(): List<ProductItem>
}