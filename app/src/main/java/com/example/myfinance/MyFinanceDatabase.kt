package com.example.myfinance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfinance.model.entity.Account
import com.example.myfinance.model.entity.Profile
import com.example.myfinance.model.entity.Transaction
import com.example.myfinance.model.entity.TransactionType

@Database(entities = [Profile::class, Account::class, Transaction::class, TransactionType::class], version = 1)
abstract class MyFinanceDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: MyFinanceDatabase? = null

        fun getDatabase(context: Context): MyFinanceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyFinanceDatabase::class.java,
                    context.resources.getString(R.string.database_name)
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}