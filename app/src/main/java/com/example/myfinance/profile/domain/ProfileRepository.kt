package com.example.myfinance.profile.domain

import com.example.myfinance.account.domain.Account


interface ProfileRepository {
    suspend fun getAll(): List<Profile>

    suspend fun login(email: String, password: String): Profile?

    suspend fun get(id: Long): Profile

    suspend fun getAccounts(profile: Profile): List<Account>

    suspend fun insert(profile: Profile): Profile
}