package com.example.homework24.domain.usecase

import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.model.Post
import com.example.homework24.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke(): Flow<ResultWrapper<List<Post>>> {
        return postsRepository.getPosts()
    }
}