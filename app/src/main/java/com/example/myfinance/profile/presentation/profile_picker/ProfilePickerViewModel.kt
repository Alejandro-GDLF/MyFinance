package com.example.myfinance.profile.presentation.profile_picker

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePickerViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val _state = MutableStateFlow( ProfilePickerState() )
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val profiles = profileRepository.getAll()

            _state.update { st -> st.copy(profiles = profiles, isLoading = false) }
        }
    }

    fun selectProfile(profile: Profile) {
        sharedPreferences.edit().putLong("profile_id", profile.id!!).apply()
    }
}