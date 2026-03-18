package com.example.sportshop.ui.domain.repository

import com.example.sportshop.ui.data.mapper.toDomain
import com.example.sportshop.ui.data.remote.ProductApi
import com.example.sportshop.ui.domain.model.CategoryItem
import com.example.sportshop.ui.domain.model.ProductItem
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository {

    override suspend fun getCategories(): List<CategoryItem> {
        return productApi.getCategories().map { it.toDomain() }
    }

    override suspend fun getProducts(): List<ProductItem> {
        return productApi.getProducts().map { it.toDomain() }
    }
}