package com.example.sportshop.ui.data.mapper

import com.example.sportshop.ui.data.dto.CategoryDto
import com.example.sportshop.ui.data.dto.ProductDto
import com.example.sportshop.ui.domain.model.CategoryItem
import com.example.sportshop.ui.domain.model.ProductItem

fun ProductDto.toDomain(): ProductItem {
    return ProductItem(
        id = id,
        title = title,
        cost = cost,
        description = description,
        isBestSeller = isBestSeller == true,
        categoryTitle = category?.title.orEmpty()
    )
}

fun CategoryDto.toDomain(): CategoryItem {
    return CategoryItem(
        id = id,
        title = title
    )
}