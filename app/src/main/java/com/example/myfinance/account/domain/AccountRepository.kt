package com.example.myfinance.account.domain

import com.example.myfinance.profile.domain.Profile

interface AccountRepository {
    suspend fun getAll(): List<Account>

    suspend fun get(id: Long): Account

    suspend fun save(account: Account, profile: Profile): Account
}