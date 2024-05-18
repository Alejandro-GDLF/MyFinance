package com.example.myfinance.transaction.domain.repository

import com.example.myfinance.account.domain.Account
import com.example.myfinance.transaction.domain.model.Transaction

interface TransactionRepository {
    suspend fun read(): List<Transaction>

    suspend fun create(account: Account, transaction: Transaction): Boolean
}