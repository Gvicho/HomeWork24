package com.example.homework24.domain.usecase

import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.model.Story
import com.example.homework24.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoryUseCase@Inject constructor(
    private val storyRepository: StoryRepository
) {
    operator fun invoke(): Flow<ResultWrapper<List<Story>>> {
        return storyRepository.getStory()
    }
}