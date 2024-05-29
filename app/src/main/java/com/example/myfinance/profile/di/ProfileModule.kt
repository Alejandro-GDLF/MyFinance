package com.example.myfinance.profile.di

import com.example.myfinance.core.database.MyFinanceDatabase
import com.example.myfinance.profile.data.RoomProfileMapper
import com.example.myfinance.profile.data.dao.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    @Singleton
    fun providesProfileDao(database: MyFinanceDatabase): ProfileDao
        = database.profileDao()

    @Provides
    @Singleton
    fun providesProfileMapper() = RoomProfileMapper()
}