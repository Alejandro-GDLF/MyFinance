package com.example.myfinance.account.data.repository

import android.util.Log
import com.example.myfinance.account.data.dao.AccountDao
import com.example.myfinance.account.data.entity.AccountEntity
import com.example.myfinance.account.data.mapper.RoomAccountMapper
import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.transaction.data.persistance.repository.RoomTransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
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
        val account =  if( id == 0L ) {
            val _account = AccountEntity(
                0L,
                0L,
                "Recently created",
                Instant.now().epochSecond
            )

            accountDao.insert(_account)
            _account
        }
        else accountDao.get(id)

        Log.d("RoomAccountRepository", "Account with id = ${id} is ${account}")

        return@withContext mapper.toDomain(
            account,
            transactionRepository.getAllByAccount(id)
        )
    }

    override suspend fun save(account: Account, profile: Profile) = withContext(Dispatchers.IO){
        val accountEntity = mapper.toPersistance(account, profile)

        val accountId = accountDao.insert(accountEntity)

        val newAccountEntity = accountEntity.copy(id = accountId)

        return@withContext mapper.toDomain(
            newAccountEntity,
            transactionRepository.getAllByAccount(newAccountEntity.id)
        )
    }
}