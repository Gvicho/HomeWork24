package com.example.homework24.data.source.remote.service

import com.example.homework24.data.source.remote.model.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {
    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPostsList(): Response<List<PostDto>>
}