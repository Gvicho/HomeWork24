package com.example.homework24.di

import com.example.homework24.presentation.screen.home.dispatcher.DefaultDispatchers
import com.example.homework24.presentation.screen.home.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DispatcherProviderModule {

    @Provides
    fun provideDefaultDispatchersProvider():DispatcherProvider{
        return DefaultDispatchers()
    }

}