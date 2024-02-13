package com.example.homework24.presentation.mappers

import com.example.homework24.domain.model.Story
import com.example.homework24.presentation.model.StoryUI

fun Story.toUI():StoryUI{
    return StoryUI(
        id = id,
        cover = cover,
        title = title
    )
}