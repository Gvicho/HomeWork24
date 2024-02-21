package com.example.homework24.domain.usecase

import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.model.Post
import com.example.homework24.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase(
    private val postsRepository: PostsRepository
) : AbstractGetPostsUseCase{
    override operator fun invoke(): Flow<ResultWrapper<List<Post>>> {
        return postsRepository.getPosts()
    }
}