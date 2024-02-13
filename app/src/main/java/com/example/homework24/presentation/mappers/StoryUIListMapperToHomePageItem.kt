package com.example.homework24.presentation.mappers

import com.example.homework24.presentation.model.HomePageItemUI
import com.example.homework24.presentation.model.ItemType
import com.example.homework24.presentation.model.StoryUI

fun List<StoryUI>.toHomePageItem():HomePageItemUI{
    return HomePageItemUI(
        id = 0,
        itemType = ItemType.STORY_RECYCLER,
        storyList = this,
        post = null
    )
}