package com.example.myfinance.profile.di

import com.example.myfinance.profile.data.RoomProfileRepository
import com.example.myfinance.profile.domain.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractProfileModule {
    @Binds
    @Singleton
    abstract fun bindsProfileRepository(profileRepo: RoomProfileRepository): ProfileRepository
}