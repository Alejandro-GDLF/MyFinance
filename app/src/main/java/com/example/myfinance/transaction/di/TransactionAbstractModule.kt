package com.example.myfinance.transaction.di

import com.example.myfinance.core.Mapper
import com.example.myfinance.transaction.data.persistance.mapper.MFDatabaseTransactionMapper
import com.example.myfinance.transaction.data.persistance.mapper.MFDbTransactionTypeMapper
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity
import com.example.myfinance.transaction.data.persistance.entity.TransactionTypeEntity
import com.example.myfinance.transaction.data.persistance.repository.MFDatabaseTransactionRepository
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.model.TransactionType
import com.example.myfinance.transaction.domain.repository.TransactionRepository
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
    abstract fun bindTransactionRepository( repository: MFDatabaseTransactionRepository)
        : TransactionRepository

    @Binds
    @Singleton
    abstract fun bindTransactionMapper( mapper: MFDatabaseTransactionMapper)
        : Mapper<TransactionEntity, Transaction>

    @Binds
    @Singleton
    abstract fun bindTransactionTypeMapper( mapper: MFDbTransactionTypeMapper)
        : Mapper<TransactionType, TransactionTypeEntity>
}