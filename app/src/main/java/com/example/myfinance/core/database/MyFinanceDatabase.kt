package com.example.myfinance.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfinance.R
import com.example.myfinance.account.data.entity.Account
import com.example.myfinance.profile.data.dto.Profile
import com.example.myfinance.transaction.data.entity.Transaction
import com.example.myfinance.transaction.data.entity.TransactionType

@Database(
    entities = [Profile::class, Account::class, Transaction::class, TransactionType::class],
    version = 1
)
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