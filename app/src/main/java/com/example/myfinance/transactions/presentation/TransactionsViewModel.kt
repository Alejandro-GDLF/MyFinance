package com.example.myfinance.transactions.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.home.presentation.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val accountRepository: AccountRepository,
    private val profileRepository: ProfileRepository
): ViewModel() {
    private lateinit var profile: Profile

    var _state = MutableStateFlow(HomeState(
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy"),
        currencyFormatter = CurrencyAmountFormatter(),
    ))
    val state get() = _state.asStateFlow()

    fun loadAccountData(accountId: String) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            try {
                // Simular la carga de datos
                val account = fetchAccountData(accountId)
                state = state.copy(account = account, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }

    private suspend fun fetchAccountData(accountId: String): Account {
        // Simulación de una llamada de red o base de datos
        return Account // Reemplazar con la lógica de obtención real
    }
}
