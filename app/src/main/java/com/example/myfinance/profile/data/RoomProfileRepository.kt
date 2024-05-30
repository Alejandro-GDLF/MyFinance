package com.example.myfinance.profile.data

import android.util.Log
import com.example.myfinance.account.data.dao.AccountDao
import com.example.myfinance.account.data.mapper.RoomAccountMapper
import com.example.myfinance.account.domain.Account
import com.example.myfinance.profile.data.dao.ProfileDao
import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import com.example.myfinance.transaction.data.persistance.repository.RoomTransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomProfileRepository @Inject constructor(
    val profileDao: ProfileDao,
    val profileMapper: RoomProfileMapper,
    val accountMapper: RoomAccountMapper,
    val accountDao: AccountDao,
    val transactionRepository: RoomTransactionRepository
): ProfileRepository {
    override suspend fun getAll(): List<Profile> = withContext(Dispatchers.IO) {
        val profiles = profileDao.getAll()

        return@withContext profiles.map { profileMapper.toDomain(it) }
    }

    override suspend fun get(id: Long): Profile = withContext(Dispatchers.IO) {
        val profileEntity = profileDao.get(id)

        return@withContext profileMapper.toDomain(profileEntity!!)
    }

    override suspend fun login(email: String, password: String): Profile? = withContext(Dispatchers.IO) {
        var profile = profileDao.get(email)
        if( profile == null ) {
            val newProfile = com.example.myfinance.profile.data.dto.ProfileEntity(
                id = 0L,
                name = "Prueba",
                email = email
            )

            val id = profileDao.insert(newProfile)

            profile = newProfile.copy(id = id)
        }

        Log.d(RoomProfileRepository::class.toString(), "Requested profile ${profile}")

        return@withContext profileMapper.toDomain(profile)
    }

    override suspend fun getAccounts(profile: Profile): List<Account> = withContext(Dispatchers.IO){
        val accounts = accountDao.getByProfile(profile.id!!)
        Log.d("Res", "Accounts retrivedd ${accounts}\n${accounts.size}")
        return@withContext accounts.map {
            accountMapper.toDomain(
                it,
                transactionRepository.getAllByAccount(it.id)
            )
        }
    }

    override suspend fun insert(profile: Profile): Profile = withContext(Dispatchers.IO){
        val profileEntity = profileMapper.toData(profile)
        val producedId = profileDao.insert(profileEntity)
        val newProfileEntity = profileEntity.copy(id = producedId)

        Log.d("Res", "Produced object ${newProfileEntity}")

        return@withContext profileMapper.toDomain(newProfileEntity)
    }
}