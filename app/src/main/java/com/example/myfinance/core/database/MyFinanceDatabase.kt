package com.example.myfinance.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfinance.account.data.entity.Account
import com.example.myfinance.profile.data.dto.Profile
import com.example.myfinance.transaction.data.persistance.dao.TransactionDao
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity
import com.example.myfinance.transaction.data.persistance.entity.TransactionTypeEntity

@Database(
    entities = [Profile::class, Account::class, TransactionEntity::class, TransactionTypeEntity::class],
    version = 1
)
abstract class MyFinanceDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}