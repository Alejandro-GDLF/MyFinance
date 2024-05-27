package com.example.myfinance.account.di

import com.example.myfinance.account.data.repository.RoomAccountRepository
import com.example.myfinance.account.domain.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractAccountModule {
    @Binds
    @Singleton
    abstract fun bindAccountRepository(accountRepository: RoomAccountRepository): AccountRepository
}