package com.example.myfinance.profile.presentation.login

data class LoginState(
    var email: String = "",
    var password: String = "",
    var isLoading: Boolean = false,
    var isSignedIn: Boolean = false
)