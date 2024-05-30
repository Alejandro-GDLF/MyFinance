package com.example.myfinance.account.presentation.new_account

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.AccountFactory
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import com.example.myfinance.profile.domain.use_cases.GetProfileByIdUseCase
import com.example.myfinance.profile.domain.use_cases.SaveAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewAccountViewModel @Inject constructor(
    private val accountFactory: AccountFactory,
    sharedPreferences: SharedPreferences,
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val saveAccountUseCase: SaveAccountUseCase
): ViewModel() {
    private var _state = MutableStateFlow(NewAccountState())
    val state get() = _state.asStateFlow()

    private lateinit var profile: Profile

    init {
        viewModelScope.launch {
            profile = getProfileByIdUseCase(
                sharedPreferences.getLong("profile_id", 0L)
            )
        }
    }

    fun createAccount(state: NewAccountState) {
        val account = accountFactory.create(state.number, state.createDate)

        viewModelScope.launch {
            val savedAccount =saveAccountUseCase(account, profile)
            Log.d("Res", "Account created with id: ${savedAccount}")
        }
    }

    fun updateNumber(number: String) {
        _state.update { st -> st.copy(number=number) }
    }
}