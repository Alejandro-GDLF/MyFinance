package com.example.myfinance.transaction.presentation.new_transaction

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import com.example.myfinance.transaction.domain.TransactionFactory
import com.example.myfinance.transaction.domain.model.TransactionType
import com.example.myfinance.transaction.domain.repository.TransactionRepository
import com.example.myfinance.transaction.domain.repository.TransactionTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.util.Currency
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewTransactionViewModel @Inject constructor(
    sharedPreferences: SharedPreferences,
    private val profileRepository: ProfileRepository,
    private val transactionRepository: TransactionRepository,
    private val transactionTypeRepository: TransactionTypeRepository,
    private val transactionFactory: TransactionFactory
) : ViewModel() {
    private var _state = MutableStateFlow(NewTransactionState())
    val state get() = _state.asStateFlow()

    private lateinit var profile: Profile

    init {
        viewModelScope.launch {
            profile = profileRepository.get(sharedPreferences.getLong("profile_id", 0L))

            val accounts = profileRepository.getAccounts(profile)
            val types = transactionTypeRepository.getAll()

            _state.update { st -> st.copy(
                accounts = accounts,
                transactionType = types,
                isLoading = false,
            ) }
        }
    }

    fun refreshTransactionTypes() {
        viewModelScope.launch {
            val transactionType = transactionTypeRepository.getAll()
            _state.update { st -> st.copy(transactionType = transactionType) }
        }
    }

    fun updateSelectedAccount(account: Account) {
        _state.update { st -> st.copy(selectedAccount = account) }
    }

    fun updateDescription(description: String) {
        _state.update { st -> st.copy(description = description) }
    }

    fun updateDate(date: Date) {
        _state.update { st -> st.copy(
            date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        ) }
    }

    fun updateAmount(amount: String) {
        _state.update { st -> st.copy(amount = amount) }
    }

    fun updateType(type: TransactionType) {
        _state.update { st -> st.copy(type = type) }
    }

    fun onCreateTransaction() {
        val description = _state.value.description
        val account = _state.value.selectedAccount
        val type = _state.value.type
        val amount = _state.value.amount?.toBigDecimalOrNull()
        val date = _state.value.date

        if (description == null || account == null || type == null || amount == null || date == null){
            Log.d("CNT", "$description $account $type $amount $date")
            return
    }

        val transaction = transactionFactory.create(
            description = description,
            type = type,
            amount = CurrencyAmount(amount, Currency.getInstance("EUR")),
            date = date
        )

        Log.d("Res", "Introducing new transaction $transaction")

        viewModelScope.launch {
            transactionRepository.create(account, transaction)
            _state.update { st -> st.copy(isCreated = true) }
        }
    }
}
