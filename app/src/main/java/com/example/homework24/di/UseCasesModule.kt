package com.example.homework24.di

import com.example.homework24.domain.repository.PostsRepository
import com.example.homework24.domain.repository.StoryRepository
import com.example.homework24.domain.usecase.AbstractGetPostsUseCase
import com.example.homework24.domain.usecase.AbstractGetStoryUseCase
import com.example.homework24.domain.usecase.GetPostsUseCase
import com.example.homework24.domain.usecase.GetStoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun provideStoryUseCase(storyRepository:StoryRepository):AbstractGetStoryUseCase{
        return GetStoryUseCase(
            storyRepository = storyRepository
        )
    }

    @Provides
    fun providePostsUseCase(postsRepository:PostsRepository):AbstractGetPostsUseCase{
        return GetPostsUseCase(
            postsRepository = postsRepository
        )
    }
}