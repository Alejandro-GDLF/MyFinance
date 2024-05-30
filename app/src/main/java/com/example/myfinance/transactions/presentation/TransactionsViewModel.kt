package com.example.myfinance.transactions.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update

import javax.inject.Inject


@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val profileRepository: ProfileRepository
): ViewModel() {
    private lateinit var profile: Profile

    private var _state = MutableStateFlow(TransactionsState(
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy"),
        currencyFormatter = CurrencyAmountFormatter(),
    ))
    val state get() = _state.asStateFlow()

    init {
        val profileId = sharedPreferences.getLong("profile_id", 0L)

        viewModelScope.launch {
            profile = profileRepository.get(profileId)
            _state.update { st -> st.copy(accounts = profileRepository.getAccounts(profile)) }

            val accountId = sharedPreferences.getLong("accountId", 0L)
            val account = state.value.accounts.find { it.id == accountId } ?: state.value.accounts.firstOrNull()

            updateSelectedAccount(account)

            Log.d("Res", "Selected account ${account}")
        }
    }

    fun updateSelectedAccount(account: Account?) {
        _state.update { st -> st.copy(selectedAccount = account) }
        if(account != null) {
            sharedPreferences.edit().putLong("accountId", account.id!!).apply()
        }
    }

}
