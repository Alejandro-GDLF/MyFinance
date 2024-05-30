package com.example.myfinance.profile.domain.use_cases

import com.example.myfinance.profile.domain.Profile
import com.example.myfinance.profile.domain.ProfileRepository
import javax.inject.Inject

class GetProfileByIdUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(profileId: Long): Profile {
        return profileRepository.get(profileId)
    }
}