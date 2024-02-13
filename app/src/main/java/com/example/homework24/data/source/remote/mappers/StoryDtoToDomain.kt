package com.example.homework24.data.source.remote.mappers

import com.example.homework24.data.source.remote.model.StoryDto
import com.example.homework24.domain.model.Story

fun StoryDto.toDomain():Story{
    return Story(
        id = id,
        cover = cover,
        title = title)
}