package com.example.myfinance.transaction.di

import com.example.myfinance.core.Mapper
import com.example.myfinance.transaction.data.mapper.MFDatabaseTransactionMapper
import com.example.myfinance.transaction.data.mapper.MFDbTransactionTypeMapper
import com.example.myfinance.transaction.data.entity.TransactionDbDto
import com.example.myfinance.transaction.data.entity.TransactionTypeDbDto
import com.example.myfinance.transaction.data.repository.MFDatabaseTransactionRepository
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
    abstract fun bindTransactionRepository( repository: MFDatabaseTransactionRepository )
        : TransactionRepository

    @Binds
    @Singleton
    abstract fun bindTransactionMapper( mapper: MFDatabaseTransactionMapper)
        : Mapper<TransactionDbDto, Transaction>

    @Binds
    @Singleton
    abstract fun bindTransactionTypeMapper( mapper: MFDbTransactionTypeMapper)
        : Mapper<TransactionType, TransactionTypeDbDto>
}