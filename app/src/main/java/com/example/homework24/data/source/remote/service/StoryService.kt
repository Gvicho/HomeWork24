package com.example.homework24.data.source.remote.service

import com.example.homework24.data.source.remote.model.StoryDto
import retrofit2.Response
import retrofit2.http.GET

interface StoryService {

    @GET("1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getStoryList(): Response<List<StoryDto>>
}