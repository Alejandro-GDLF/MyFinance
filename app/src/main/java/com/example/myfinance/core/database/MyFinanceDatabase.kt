package com.example.myfinance.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfinance.account.data.dao.AccountDao
import com.example.myfinance.account.data.entity.AccountEntity
import com.example.myfinance.profile.data.dao.ProfileDao
import com.example.myfinance.profile.data.dto.ProfileEntity
import com.example.myfinance.transaction.data.persistance.dao.TransactionDao
import com.example.myfinance.transaction.data.persistance.dao.TransactionTypeDao
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity
import com.example.myfinance.transaction.data.persistance.entity.TransactionTypeEntity

@Database(
    entities = [ProfileEntity::class, AccountEntity::class, TransactionEntity::class, TransactionTypeEntity::class],
    version = 1
)
abstract class MyFinanceDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionTypeDao(): TransactionTypeDao

    abstract fun profileDao(): ProfileDao
}