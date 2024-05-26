package com.example.myfinance.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.myfinance.R
import com.example.myfinance.core.database.MyFinanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyFinanceDatabase(@ApplicationContext appContext: Context): MyFinanceDatabase {
        return Room.databaseBuilder(
            appContext,
            MyFinanceDatabase::class.java,
            appContext.getString(R.string.database_name)
        ).build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(
            appContext.getString(R.string.shared_preferences),
            Context.MODE_PRIVATE
        )
    }
}