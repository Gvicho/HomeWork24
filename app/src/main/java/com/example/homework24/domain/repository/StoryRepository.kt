package com.example.homework24.domain.repository

import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    fun getStory(): Flow<ResultWrapper<List<Story>>>
}