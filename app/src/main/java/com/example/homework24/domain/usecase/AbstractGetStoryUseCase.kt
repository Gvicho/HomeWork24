package com.example.homework24.domain.usecase

import com.example.homework24.domain.model.Story
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AbstractGetStoryUseCase {
    operator fun invoke(): Flow<ResultWrapper<List<Story>>>
}