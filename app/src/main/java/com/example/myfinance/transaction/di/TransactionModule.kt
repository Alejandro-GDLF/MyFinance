package com.example.myfinance.transaction.di

import com.example.myfinance.core.currency.CurrencyAmountFormatter
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
}