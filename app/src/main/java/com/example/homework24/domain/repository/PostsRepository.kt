package com.example.homework24.domain.repository

import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(): Flow<ResultWrapper<List<Post>>>

    fun getPostsDetails(id:Int): Flow<ResultWrapper<Post>>
}