package com.example.homework24.data.source.remote.mappers

import com.example.homework24.data.source.remote.model.PostDto
import com.example.homework24.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        images = images?: emptyList(),
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toDomain()
    )
}