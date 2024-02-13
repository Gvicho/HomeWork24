package com.example.homework24.presentation.mappers

import com.example.homework24.presentation.model.HomePageItemUI
import com.example.homework24.presentation.model.ItemType
import com.example.homework24.presentation.model.PostUI

fun PostUI.toHomePageItem():HomePageItemUI{
    return HomePageItemUI(
        id = id,
        itemType = ItemType.POST,
        storyList = null,
        post = this
    )
}