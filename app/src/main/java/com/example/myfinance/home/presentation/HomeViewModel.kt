package com.example.myfinance.home.presentation

import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val profileRepository: ProfileRepository
): ViewModel() {
    private lateinit var profile: Profile

    var _state = MutableStateFlow(HomeState(
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy"),
        currencyFormatter = CurrencyAmountFormatter(),
    ))
    val state get() = _state.asStateFlow()

    init {
        val profileId = sharedPreferences.getLong("profile_id", 0L)

        viewModelScope.launch {
            profile = profileRepository.get(profileId)
            collectAccounts()
            updateSelectedAccount(state.value.accounts.firstOrNull())
        }
    }

    fun collectAccounts() {
        viewModelScope.launch {
            val accounts = profileRepository.getAccounts(profile)
            _state.update { st -> st.copy(accounts = accounts) }
            if (accounts.isNotEmpty()) {
                updateSelectedAccount(accounts[0])
            }
        }
    }

    fun updateSelectedAccount(account: Account?) {
        _state.update { st -> st.copy(selectedAccount = account) }
    }
}