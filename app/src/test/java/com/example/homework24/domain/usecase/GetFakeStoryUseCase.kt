package com.example.homework24.domain.usecase

import com.example.homework24.domain.model.Post
import com.example.homework24.domain.model.Story
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFakeStoryUseCase:AbstractGetStoryUseCase {
    override fun invoke(): Flow<ResultWrapper<List<Story>>> = flow{
        emit(
            ResultWrapper.Success(
                listOf(
                    Story(
                        id = 0, cover = "", title = ""
                    )
                )
            )
        )
    }

}