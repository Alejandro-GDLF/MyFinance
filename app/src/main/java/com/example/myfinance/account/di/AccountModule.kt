package com.example.myfinance.account.di

import com.example.myfinance.account.data.mapper.RoomAccountMapper
import com.example.myfinance.core.database.MyFinanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {
    @Provides
    @Singleton
    fun providesAccountDao(database: MyFinanceDatabase) = database.accountDao()

    @Provides
    @Singleton
    fun providesRoomAccountMapper() = RoomAccountMapper
}