package com.example.homework24.domain.usecase

import com.example.homework24.domain.model.Post
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AbstractGetPostsUseCase {
    operator fun invoke(): Flow<ResultWrapper<List<Post>>>
}