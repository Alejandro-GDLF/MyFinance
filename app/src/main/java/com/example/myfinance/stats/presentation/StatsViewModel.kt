package com.example.myfinance.stats.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class StatsViewModel @Inject constructor(
    sharedPreferences: SharedPreferences,
    private val accountRepository: AccountRepository
): ViewModel() {
    private var _state = MutableStateFlow(StatsScreenState(
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy"),
        currencyAmountFormatter = CurrencyAmountFormatter()
    ))
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val accountId = sharedPreferences.getLong("accountId", 0L)
            val account = accountRepository.get(accountId)

            _state.update { it.copy(account=account, isLoading = false) }
        }
    }
}