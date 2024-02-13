package com.example.homework24.data.repository

import com.example.homework24.data.common.HandleResponse
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.data.common.mapResultWrapper
import com.example.homework24.data.source.remote.mappers.toDomain
import com.example.homework24.data.source.remote.service.StoryService
import com.example.homework24.domain.model.Story
import com.example.homework24.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoryRepositoryImpl@Inject constructor(
    private val storyService:StoryService,
    private val handleResponse: HandleResponse
):StoryRepository {

    override fun getStory(): Flow<ResultWrapper<List<Story>>> {
        return handleResponse.safeApiCall {
            storyService.getStoryList()
        }.mapResultWrapper {list->
            list.map {
                it.toDomain()
            }
        }
    }

}