package com.example.myfinance.home.presentation

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    sharedPreferences: SharedPreferences,
    val accountRepository: AccountRepository
): ViewModel() {

    val accountId: Long = sharedPreferences.getLong("account_id", 0L)
    var state: HomeState by mutableStateOf(HomeState(
        account = null,
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy"),
        currencyFormatter = CurrencyAmountFormatter()
    ))

    init {
        viewModelScope.launch {
            state.account = accountRepository.get(accountId)
        }
    }
}