package com.example.sportshop.ui.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshop.ui.domain.model.ProfileData
import com.example.sportshop.ui.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MenuUiState(
    val profile: ProfileData? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(MenuUiState())
    val uiState: State<MenuUiState> = _uiState

    fun loadProfile(userId: String?) {
        if (userId.isNullOrBlank()) {
            _uiState.value = MenuUiState(
                profile = ProfileData(
                    userId = "",
                    firstName = "",
                    lastName = "",
                    address = "",
                    phone = "",
                    photoUrl = null
                )
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val profile = profileRepository.getProfile(userId)

                _uiState.value = MenuUiState(
                    isLoading = false,
                    profile = profile ?: ProfileData(
                        userId = userId,
                        firstName = "",
                        lastName = "",
                        address = "",
                        phone = "",
                        photoUrl = null
                    )
                )
            } catch (e: Exception) {
                _uiState.value = MenuUiState(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки профиля",
                    profile = ProfileData(
                        userId = userId,
                        firstName = "",
                        lastName = "",
                        address = "",
                        phone = "",
                        photoUrl = null
                    )
                )
            }
        }
    }
}