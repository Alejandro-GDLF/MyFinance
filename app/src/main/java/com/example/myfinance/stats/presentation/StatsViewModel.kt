package com.example.myfinance.stats.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.transaction.domain.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale
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

            val balance = getSum(account.transactions)
            val spent = getSum(account.transactions.filter { it.amount.amount < BigDecimal.ZERO })
            val income = getSum(account.transactions.filter { it.amount.amount >= BigDecimal.ZERO })

            val typeAndTotal: Map<String, CurrencyAmount> = account.transactions
                .groupBy { it.type }
                .mapValues { getSum(it.value) }
                .mapKeys { it.key.description }

            _state.update {
                it.copy(
                    account=account,
                    isLoading = false,
                    balance = balance,
                    spent = spent,
                    income = income,
                    typeAndTotal = typeAndTotal
                )
            }
        }
    }

    private fun getSum(transactions: List<Transaction>): CurrencyAmount {
        var amount = CurrencyAmount(BigDecimal(0L), Currency.getInstance(Locale.getDefault()))
        for (transaction in transactions)
            amount = amount + transaction.amount

        return amount
    }
}