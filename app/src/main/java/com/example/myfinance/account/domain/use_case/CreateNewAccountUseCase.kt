package com.example.myfinance.account.domain.use_case

import com.example.myfinance.account.domain.Account
import com.example.myfinance.account.domain.AccountFactory
import com.example.myfinance.account.domain.AccountRepository
import com.example.myfinance.profile.domain.Profile
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * @brief Use case for creating a new account.
 *
 * This use case is created for future-proof operation, being more flexible to changes in domain
 * requirements or validations.
 *
 * Also, abstracts the presentation layer from the instantiation of domain objects.
 */
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