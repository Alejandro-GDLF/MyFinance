package com.example.myfinance.profile.data

import com.example.myfinance.profile.data.dto.ProfileEntity
import com.example.myfinance.profile.domain.Profile

class RoomProfileMapper {
    fun toDomain(profile: ProfileEntity): Profile {
        return Profile(
            id = profile.id,
            name = profile.name,
            email = profile.email
        )
    }

    fun toData(profile: Profile): ProfileEntity {
        return ProfileEntity(
            id = profile.id ?: 0L,
            name = profile.name,
            email = profile.email
        )
    }
}