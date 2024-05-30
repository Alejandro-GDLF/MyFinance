package com.example.myfinance.account.domain.use_case

import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.domain.AccountFactory
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import java.time.LocalDateTime
import javax.inject.Inject

class CreateNewAccountUseCase @Inject constructor(
    private val accountFactory: AccountFactory,
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(
        number: String,
        createDate: LocalDateTime,
        profile: Profile
    ): Account {
        val account = accountFactory.create(number, createDate)
        return accountRepository.save(account, profile)
    }
}