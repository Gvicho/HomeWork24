package com.example.homework24.presentation.model

data class HomePageItemUI(
    val id:Int,
    val itemType:ItemType,
    val storyList:List<StoryUI>? = null,
    val post: PostUI? = null
)

enum class ItemType{
    STORY_RECYCLER,
    POST
}