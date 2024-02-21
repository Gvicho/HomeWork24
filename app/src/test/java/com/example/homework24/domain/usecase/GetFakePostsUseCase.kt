package com.example.homework24.domain.usecase

import com.example.homework24.domain.model.Owner
import com.example.homework24.domain.model.Post
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFakePostsUseCase:AbstractGetPostsUseCase {
    override fun invoke(): Flow<ResultWrapper<List<Post>>> = flow {
        emit(
            ResultWrapper.Success(
                listOf(
                    Post(
                        id = 0,
                        images = listOf(),
                        title = "",
                        comments = 0,
                        likes = 0,
                        shareContent = "",
                        owner = Owner(
                            firstName = "",
                            lastName = "",
                            profile = "",
                            postDate = 0
                        )
                    )
                )
            )
        )
    }
}