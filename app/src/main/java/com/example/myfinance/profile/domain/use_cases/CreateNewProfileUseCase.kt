package com.example.myfinance.profile.domain.use_cases

import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileFactory
import com.example.myfinance.profile.domain.ProfileRepository
import javax.inject.Inject

/**
 * @brief Use case for creating a new profile.
 *
 * This use case is created for future-proof operation, being more flexible to changes in domain
 * requirements or validations.
 *
 * Also, abstracts the presentation layer from the instantiation of domain objects.
 */
class CreateNewProfileUseCase @Inject constructor(
    private val profileFactory: ProfileFactory,
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(name: String, email: String): Profile {
        val newProfile = profileFactory.create(name, email)

        return profileRepository.insert(newProfile)
    }
}