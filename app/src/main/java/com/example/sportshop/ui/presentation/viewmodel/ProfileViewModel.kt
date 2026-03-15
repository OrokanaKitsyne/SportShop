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

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val profile: ProfileData? = null,
    val editedFirstName: String = "",
    val editedLastName: String = "",
    val editedAddress: String = "",
    val editedPhone: String = "",
    val saveSuccess: Boolean = false,
    val error: String? = null
) {
    val hasChanges: Boolean
        get() {
            val original = profile ?: return false
            return editedFirstName != original.firstName ||
                    editedLastName != original.lastName ||
                    editedAddress != original.address ||
                    editedPhone != original.phone
        }
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(ProfileUiState())
    val uiState: State<ProfileUiState> = _uiState

    fun loadProfile(userId: String?) {
        if (userId.isNullOrBlank()) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = "Не найден userId"
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null, saveSuccess = false)

            try {
                val profile = profileRepository.getProfile(userId) ?: ProfileData(
                    userId = userId,
                    firstName = "",
                    lastName = "",
                    address = "",
                    phone = "",
                    photoUrl = null
                )

                _uiState.value = ProfileUiState(
                    isLoading = false,
                    profile = profile,
                    editedFirstName = profile.firstName,
                    editedLastName = profile.lastName,
                    editedAddress = profile.address,
                    editedPhone = profile.phone
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки профиля"
                )
            }
        }
    }

    fun onFirstNameChange(value: String) {
        _uiState.value = _uiState.value.copy(
            editedFirstName = value,
            saveSuccess = false
        )
    }

    fun onLastNameChange(value: String) {
        _uiState.value = _uiState.value.copy(
            editedLastName = value,
            saveSuccess = false
        )
    }

    fun onAddressChange(value: String) {
        _uiState.value = _uiState.value.copy(
            editedAddress = value,
            saveSuccess = false
        )
    }

    fun onPhoneChange(value: String) {
        _uiState.value = _uiState.value.copy(
            editedPhone = value,
            saveSuccess = false
        )
    }

    fun saveProfile() {
        val current = _uiState.value.profile ?: return
        if (!_uiState.value.hasChanges) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSaving = true, error = null, saveSuccess = false)

            val updatedProfile = current.copy(
                firstName = _uiState.value.editedFirstName.trim(),
                lastName = _uiState.value.editedLastName.trim(),
                address = _uiState.value.editedAddress.trim(),
                phone = _uiState.value.editedPhone.trim()
            )

            val result = profileRepository.saveProfile(updatedProfile)

            result.fold(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(
                        isSaving = false,
                        profile = updatedProfile,
                        editedFirstName = updatedProfile.firstName,
                        editedLastName = updatedProfile.lastName,
                        editedAddress = updatedProfile.address,
                        editedPhone = updatedProfile.phone,
                        saveSuccess = true
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isSaving = false,
                        error = error.message ?: "Ошибка сохранения"
                    )
                }
            )
        }
    }
}