package com.example.myfinance.profile.domain.use_cases

import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import javax.inject.Inject

class SaveAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: Account, profile: Profile): Account {
        return accountRepository.save(account, profile)
    }
}