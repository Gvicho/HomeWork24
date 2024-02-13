package com.example.homework24.presentation.mappers

import com.example.homework24.domain.model.Owner
import com.example.homework24.presentation.model.OwnerUI

fun Owner.toUI():OwnerUI{
    return OwnerUI(
        name = firstName + lastName,
        profile = profile,
        postDate = postDate
    )
}