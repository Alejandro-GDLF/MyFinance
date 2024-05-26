package com.example.myfinance.account.data.repository

import com.example.myfinance.account.data.dao.AccountDao
import com.example.myfinance.account.data.entity.AccountEntity
import com.example.myfinance.account.data.mapper.RoomAccountMapper
import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.transaction.data.persistance.repository.RoomTransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomAccountRepository @Inject constructor(
    private val accountDao: AccountDao,
    private val transactionRepository: RoomTransactionRepository,
    private val mapper: RoomAccountMapper
): AccountRepository {
    override suspend fun getAll(): List<Account> = withContext(Dispatchers.IO) {
        val accounts: List<AccountEntity> = accountDao.getAll()

        return@withContext accounts.map { accountEntity ->
            mapper.toDomain(
                accountEntity,
                transactionRepository.getAllByAccount(accountEntity.id)
            )
        }
    }

    override suspend fun get(id: Long): Account = withContext(Dispatchers.IO) {
        val account = accountDao.get(id)

        return@withContext mapper.toDomain(
            account,
            transactionRepository.getAllByAccount(id)
        )
    }

    override suspend fun save(account: Account, profile: Profile) {
        val accountEntity = mapper.toPersistance(account, profile)
    }
}