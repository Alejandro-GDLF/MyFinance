package com.example.myfinance.profile.presentation.new_profile

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.profile.domain.ProfileFactory
import com.example.myfinance.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewProfileViewModel @Inject constructor(
    private val profileFactory: ProfileFactory,
    private val profileRepository: ProfileRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val _state = MutableStateFlow(NewProfileState())
    val state get() = _state.asStateFlow()

    fun createNewProfile(state: NewProfileState) {
        val name = state.name
        val email = state.email

        Log.d("NewProfile", "Creting new Profile ${name} // ${email}")

        val profile = profileFactory.create(name, email)
        _state.update { st -> st.copy(isLoading = true) }

        viewModelScope.launch {
            val newProfile = profileRepository.insert(profile)

            sharedPreferences.edit().putLong("profile_id", newProfile.id!!).apply()

            _state.update { st -> st.copy(isLoading = false, isCreated = true) }
        }
    }

    fun updateName(name: String ) {
        _state.update { st -> st.copy(name = name) }
    }

    fun updateEmail(email:String) {
        _state.update { st -> st.copy(email = email) }
    }
}