package com.example.homework24.data.source.remote.mappers

import com.example.homework24.data.source.remote.model.OwnerDto
import com.example.homework24.domain.model.Owner

fun OwnerDto.toDomain():Owner{
    return Owner(
        firstName = firstName,
        lastName = lastName,
        profile = profile?:"",
        postDate = postDate)
}