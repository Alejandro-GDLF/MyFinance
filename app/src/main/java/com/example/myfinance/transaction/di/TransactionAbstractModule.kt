package com.example.myfinance.transaction.di

import com.example.myfinance.transaction.data.persistance.repository.RoomTransactionRepository
import com.example.myfinance.transaction.data.persistance.repository.RoomTransactionTypeRepository
import com.example.myfinance.transaction.domain.repository.TransactionRepository
import com.example.myfinance.transaction.domain.repository.TransactionTypeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionAbstractModule {

    @Binds
    @Singleton
    abstract fun bindTransactionRepository( repository: RoomTransactionRepository)
        : TransactionRepository

    @Binds
    @Singleton
    abstract fun bindTransactionTypeRepository(repository: RoomTransactionTypeRepository)
        : TransactionTypeRepository
}