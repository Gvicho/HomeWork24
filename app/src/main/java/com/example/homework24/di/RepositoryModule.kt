package com.example.homework24.di

import com.example.homework24.data.common.HandleResponse
import com.example.homework24.data.repository.PostsRepositoryImpl
import com.example.homework24.data.repository.StoryRepositoryImpl
import com.example.homework24.data.source.remote.service.PostsService
import com.example.homework24.data.source.remote.service.StoryService
import com.example.homework24.domain.repository.PostsRepository
import com.example.homework24.domain.repository.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {



    @Singleton
    @Provides
    fun providePostsRepository(
        postsService:PostsService,
        handleResponse: HandleResponse
    ): PostsRepository {
        return PostsRepositoryImpl(
            postsService = postsService,
            handleResponse = handleResponse
        )
    }
    @Singleton
    @Provides
    fun provideStoryRepository(
        storyService: StoryService,
        handleResponse: HandleResponse
    ): StoryRepository {
        return StoryRepositoryImpl(
            storyService = storyService,
            handleResponse = handleResponse
        )
    }


}