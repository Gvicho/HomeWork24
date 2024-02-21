package com.example.homework24.presentation.screen.home

import com.example.homework24.domain.model.Owner
import com.example.homework24.domain.model.Post
import com.example.homework24.domain.model.Story
import com.example.homework24.domain.usecase.GetFakePostsUseCase
import com.example.homework24.domain.usecase.GetFakeStoryUseCase
import com.example.homework24.presentation.event.HomePageEvents
import com.example.homework24.presentation.mappers.toHomePageItem
import com.example.homework24.presentation.mappers.toUI
import com.example.homework24.presentation.screen.dipatchers.TestDispatchers
import com.example.homework24.presentation.state.HomePageState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.Test

class HomePageViewModelTest {

    lateinit var viewModel:HomePageViewModel

    @Before
    fun init(){
        viewModel = HomePageViewModel(
            getPostsUseCase = GetFakePostsUseCase(),
            getStoryUseCase = GetFakeStoryUseCase(),
            dispatchers = TestDispatchers()
        )
    }

    @Test
    fun getUiStateFlow() = runTest{

        viewModel.onEvent(HomePageEvents.LoadPage)

        val theValue = HomePageState(isSuccess = listOf(
            listOf(
                Story(
                    id = 0, cover = "", title = ""
                )
            ).map { it.toUI() }.toHomePageItem(),
            Post(
                id = 0,
                images = listOf(),
                title = "",
                comments = 0,
                likes = 0,
                shareContent = "",
                owner = Owner(
                    firstName = "",
                    lastName = "",
                    profile = "",
                    postDate = 0
                )
            ).toUI().toHomePageItem()
        ))

        Assertions.assertEquals(viewModel.uiStateFlow.value, theValue)

    }

}