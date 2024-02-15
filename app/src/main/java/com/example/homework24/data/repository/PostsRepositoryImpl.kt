package com.example.homework24.data.repository

import com.example.homework24.data.common.HandleResponse
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.data.common.mapResultWrapper
import com.example.homework24.data.source.remote.mappers.toDomain
import com.example.homework24.data.source.remote.service.PostsService
import com.example.homework24.domain.model.Post
import com.example.homework24.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl@Inject constructor(
    private val postsService:PostsService,
    private val handleResponse:HandleResponse
) :PostsRepository{

    override fun getPosts(): Flow<ResultWrapper<List<Post>>> {
        return handleResponse.safeApiCall {
            postsService.getPostsList()
        }.mapResultWrapper {list->
            list.map {
                it.toDomain()
            }
        }
    }

    override fun getPostsDetails(id:Int): Flow<ResultWrapper<Post>> {
        return handleResponse.safeApiCall {
            postsService.getPostDetails(id)
        }.mapResultWrapper {post->
            post.toDomain()
        }
    }
}