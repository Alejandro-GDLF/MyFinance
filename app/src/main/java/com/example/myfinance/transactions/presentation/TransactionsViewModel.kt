package com.example.myfinance.transactions.presentation

import android.content.SharedPreferences
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
    private val accountRepository: AccountRepository,
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
            val accountId = sharedPreferences.getLong("accountId", 0L)
            if (accountId != 0L){
                val selectedAccount = state.value.accounts.find { it.id == accountId }
                updateSelectedAccount(selectedAccount)
            }else{
                updateSelectedAccount(state.value.accounts.firstOrNull())
            }
        }
    }

    fun loadAccountData(accountId: Long) {
        _state.update{ st -> st.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val account = accountRepository.get(accountId)
                _state.update{st -> st.copy(selectedAccount = account, isLoading = false)}
            } catch (e: Exception) {
                _state.update{st -> st.copy(isLoading = false, errorMessage = e.message)}
            }
        }
    }
    fun updateSelectedAccount(account: Account?) {
        _state.update { st -> st.copy(selectedAccount = account) }
        if(account != null) {
            sharedPreferences.edit().putLong("accountId", account.id!!).apply()
        }
    }

}
