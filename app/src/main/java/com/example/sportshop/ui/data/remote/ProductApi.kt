package com.example.sportshop.ui.data.remote

import com.example.sportshop.ui.data.dto.CategoryDto
import com.example.sportshop.ui.data.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("rest/v1/categories")
    suspend fun getCategories(
        @Query("select") select: String = "id,title",
        @Query("order") order: String = "title.asc"
    ): List<CategoryDto>

    @GET("rest/v1/products")
    suspend fun getProducts(
        @Query("select") select: String = "id,title,cost,description,is_best_seller,category:categories(title)",
        @Query("order") order: String = "is_best_seller.desc,title.asc"
    ): List<ProductDto>
}