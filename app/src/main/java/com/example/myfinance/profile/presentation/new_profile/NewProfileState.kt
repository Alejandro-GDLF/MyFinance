package com.example.myfinance.profile.presentation.new_profile

data class NewProfileState (
    var name: String = "",
    var email: String = "",
    val isLoading: Boolean = false,
    val isCreated: Boolean = false
)