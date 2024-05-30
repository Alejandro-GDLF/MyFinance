package com.example.myfinance.profile.domain.use_cases

import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileFactory
import com.example.myfinance.profile.domain.ProfileRepository
import javax.inject.Inject

class CreateNewProfileUseCase @Inject constructor(
    private val profileFactory: ProfileFactory,
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(name: String, email: String): Profile {
        val newProfile = profileFactory.create(name, email)

        return profileRepository.insert(newProfile)
    }
}