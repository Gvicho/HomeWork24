package com.example.homework24.domain.usecase

import com.example.homework24.domain.model.Post
import com.example.homework24.domain.repository.PostsRepository
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke(id:Int): Flow<ResultWrapper<Post>> {
        return postsRepository.getPostsDetails(id)
    }
}