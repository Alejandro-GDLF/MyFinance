package com.example.myfinance.transaction.di

import com.example.myfinance.core.currency.CurrencyAmountFormatter
import com.example.myfinance.core.database.MyFinanceDatabase
import com.example.myfinance.transaction.data.persistance.mapper.RoomTransactionMapper
import com.example.myfinance.transaction.data.persistance.mapper.RoomTransactionTypeMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {
    @Provides
    @Singleton
    fun provideDateTimeFormatter(): DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd/MM/yy")

    @Provides
    @Singleton
    fun provideCurrencyAmountFormatter(): CurrencyAmountFormatter = CurrencyAmountFormatter()

    @Provides
    @Singleton
    fun provideTransactionDao(database: MyFinanceDatabase) = database.transactionDao()

    @Provides
    @Singleton
    fun provideTransactionTypeDao(database: MyFinanceDatabase) = database.transactionTypeDao()

    @Provides
    @Singleton
    fun providesTransactionMapper() = RoomTransactionMapper

    @Provides
    @Singleton
    fun providesTransactionTypeMapper() = RoomTransactionTypeMapper
}