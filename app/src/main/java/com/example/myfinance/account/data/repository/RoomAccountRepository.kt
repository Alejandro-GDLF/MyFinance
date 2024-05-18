package com.example.myfinance.account.data.repository

import com.example.myfinance.account.data.dao.AccountDao
import com.example.myfinance.account.data.entity.AccountEntity
import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.core.Mapper
import javax.inject.Inject

class RoomAccountRepository @Inject constructor(
    val accountDao: AccountDao,
    val mapper: Mapper<AccountEntity, Account>
): AccountRepository {
    override suspend fun read(): List<Account> {
        val accounts: List<AccountEntity> = accountDao.getAll()

        return accounts.map { mapper.to(it) }
    }

    override suspend fun save(account: Account) {
        TODO("Not yet implemented")
    }
}