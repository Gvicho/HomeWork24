package com.example.homework24.presentation.mappers

import com.example.homework24.domain.model.Post
import com.example.homework24.presentation.model.PostUI

fun Post.toUI():PostUI{
    return PostUI(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toUI()
    )
}