package com.example.myfinance.account.domain

import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.repository.TransactionRepository
import javax.inject.Inject

class AccountService @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend fun addTransaction(account: Account, transaction: Transaction) {
        val isTransactionAccepted = account.addTransaction(transaction)

        if( isTransactionAccepted )
            this.transactionRepository.create(account, transaction)
    }
}