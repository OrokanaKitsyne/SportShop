package com.example.sportshop.ui.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sportshop.ui.domain.model.CategoryItem
import com.example.sportshop.ui.domain.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val categories: List<CategoryItem> = emptyList(),
    val products: List<ProductItem> = emptyList(),
    val error: String? = null,
    val selectedCategory: String = "Все"
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = mutableStateOf(
        HomeUiState(
            categories = listOf(
                CategoryItem("all", "Все"),
                CategoryItem("1", "Outdoor"),
                CategoryItem("2", "Tennis")
            ),
            products = listOf(
                ProductItem(
                    id = "1",
                    title = "Nike Air Max",
                    cost = 7520.0,
                    description = "Кроссовки",
                    isBestSeller = true,
                    categoryTitle = "Outdoor"
                ),
                ProductItem(
                    id = "2",
                    title = "Nike Revolution",
                    cost = 6890.0,
                    description = "Кроссовки",
                    isBestSeller = true,
                    categoryTitle = "Tennis"
                )
            )
        )
    )

    val uiState: State<HomeUiState> = _uiState

    fun selectCategory(category: String) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }
}