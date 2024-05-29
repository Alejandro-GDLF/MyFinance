package com.example.myfinance.profile.presentation.profile_picker

import com.example.myfinance.profile.domain.Profile

data class ProfilePickerState (
    val profiles: List<Profile> = listOf(),
    val isLoading: Boolean = true
)