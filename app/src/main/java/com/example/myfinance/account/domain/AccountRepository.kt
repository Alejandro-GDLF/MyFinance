package com.example.myfinance.account.domain

interface AccountRepository {
    suspend fun read(): List<Account>

    suspend fun save(account: Account)
}