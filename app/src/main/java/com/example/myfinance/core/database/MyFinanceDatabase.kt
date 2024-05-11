package com.example.myfinance.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfinance.account.data.entity.Account
import com.example.myfinance.profile.data.dto.Profile
import com.example.myfinance.transaction.data.dao.TransactionDao
import com.example.myfinance.transaction.data.entity.TransactionDbDto
import com.example.myfinance.transaction.data.entity.TransactionTypeDbDto

@Database(
    entities = [Profile::class, Account::class, TransactionDbDto::class, TransactionTypeDbDto::class],
    version = 1
)
abstract class MyFinanceDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}