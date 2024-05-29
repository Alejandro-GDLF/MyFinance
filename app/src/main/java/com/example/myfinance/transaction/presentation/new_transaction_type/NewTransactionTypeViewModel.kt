package com.example.myfinance.transaction.presentation.new_transaction_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.transaction.domain.TransactionTypeFactory
import com.example.myfinance.transaction.domain.repository.TransactionTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTransactionTypeViewModel @Inject constructor(
    private val transactionTypeRepository: TransactionTypeRepository,
    private val transactionTypeFactory: TransactionTypeFactory
): ViewModel() {
    private var _state = MutableStateFlow(NewTransactionTypeState())
    val state get() = _state.asStateFlow()

    fun updateDescription(description: String) {
        _state.update { st -> st.copy(description = description) }
    }

    fun create() {
        val description = _state.value.description

        val transactionType = transactionTypeFactory.create(description)

        viewModelScope.launch {
            transactionTypeRepository.save(transactionType)

            _state.update { st -> st.copy(isCreated = true) }
        }
    }
}