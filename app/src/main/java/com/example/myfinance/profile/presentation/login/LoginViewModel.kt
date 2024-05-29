package com.example.myfinance.profile.presentation.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myfinance.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val profileRepository: ProfileRepository
): ViewModel() {
    private var _state = MutableStateFlow(LoginState())
    val state get() = _state.asStateFlow()

    fun login(email: String, password: String) {
        Log.d(LoginViewModel::class.toString(), "Login in ${email}, password: ${password}")
        _state.update { st -> st.copy(isLoading = true) }
        viewModelScope.launch {
            val profile = profileRepository.login(email, password)

            profile?.id?.let { sharedPreferences.edit().putLong("profile_id", it).apply() }

            _state.update { st -> st.copy(isSignedIn = true) }
        }
    }
}