package com.example.homework24.presentation.screen.home

import com.example.homework24.domain.model.Owner
import com.example.homework24.domain.model.Post
import com.example.homework24.domain.model.Story
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.usecase.AbstractGetPostsUseCase
import com.example.homework24.domain.usecase.AbstractGetStoryUseCase
import com.example.homework24.presentation.event.HomePageEvents
import com.example.homework24.presentation.mappers.toHomePageItem
import com.example.homework24.presentation.mappers.toUI
import com.example.homework24.presentation.model.HomePageItemUI
import com.example.homework24.presentation.screen.dipatchers.TestDispatchers
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomePageViewModelTest {

    private lateinit var viewModel:HomePageViewModel
    private val testDispatcher = TestDispatchers()
    private val mockGetPostsUseCase = mockk<AbstractGetPostsUseCase>()
    private val mockGetStoryUseCase = mockk<AbstractGetStoryUseCase>()

    private fun getFakePostWrappedInHomePageItem(): HomePageItemUI = Post(
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


    private fun getFakeStoryListWrappedInHomePageItem():HomePageItemUI =  listOf(
        Story(
            id = 0, cover = "", title = ""
        )
    ).map { it.toUI() }.toHomePageItem()

    private fun getFakePost(): List<Post> = listOf(
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
        ))
    )


    private fun getFakeStory():List<Story> =  listOf(
        Story(
            id = 0, cover = "", title = ""
        )
    )


    @Before
    fun setup(){


        viewModel = HomePageViewModel(
            getPostsUseCase = mockGetPostsUseCase,
            getStoryUseCase = mockGetStoryUseCase,
            dispatchers = testDispatcher
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `LoadPage success scenario`() = runTest(testDispatcher.main) {
        coEvery { mockGetPostsUseCase() } returns flowOf(
            ResultWrapper.Success(getFakePost())
        )
        coEvery { mockGetStoryUseCase() } returns flowOf(
            ResultWrapper.Success(getFakeStory())
        )
        //Get
        val fakePostHomePageUIITem =getFakePostWrappedInHomePageItem()
        val fakeStoryListHomePageUIITem = getFakeStoryListWrappedInHomePageItem()

        // When
        viewModel.onEvent(HomePageEvents.LoadPage)
        advanceUntilIdle()

        // Then
        val stateList = viewModel.uiStateFlow.value.isSuccess
        assertThat(stateList).isEqualTo(listOf(fakeStoryListHomePageUIITem,fakePostHomePageUIITem))
    }

    @Test
    fun `Navigation Test`() = runTest(testDispatcher.main){
        //Get

        // When
        viewModel.onEvent(HomePageEvents.OpenDetailsPage(123))

        //Then
        val navigationEvent = viewModel.navigationEventFlow.first()
        assertThat(navigationEvent).isInstanceOf(HomePageNavigationEvent.NavigateToDetails::class.java)
        assertThat((navigationEvent as HomePageNavigationEvent.NavigateToDetails).id).isEqualTo(123)
    }


    @Test
    fun `LoadPage Story Error scenario`() = runTest(testDispatcher.main) {
        coEvery { mockGetStoryUseCase() } returns flowOf(
            ResultWrapper.Error("a")
        )
        coEvery { mockGetPostsUseCase() } returns flowOf(
            ResultWrapper.Success(getFakePost())
        )
        //Get
        val errorMessage = "a"

        // When
        viewModel.onEvent(HomePageEvents.LoadPage)


        // Then
        val eventMessage = viewModel.oneTimeEventFlow.first()
        assertThat(eventMessage).isEqualTo(errorMessage)
    }

    @Test
    fun `LoadPage Post Error scenario`() = runTest(testDispatcher.main) {
        coEvery { mockGetPostsUseCase() } returns flowOf(
            ResultWrapper.Error("a")
        )
        coEvery { mockGetStoryUseCase() } returns flowOf(
            ResultWrapper.Success(getFakeStory())
        )
        //Get
        val errorMessage = "a"

        // When
        viewModel.onEvent(HomePageEvents.LoadPage)


        // Then
        val eventMessage = viewModel.oneTimeEventFlow.first()
        assertThat(eventMessage).isEqualTo(errorMessage)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `LoadPage Loading Story (it only emits true it is starter) scenario`() = runTest(testDispatcher.main) {
        coEvery { mockGetStoryUseCase() } returns flowOf(
            ResultWrapper.Loading(true)
        )
        coEvery { mockGetPostsUseCase() } returns flowOf(
            ResultWrapper.Success(getFakePost())
        )
        //Get
        val errorMessage = true

        // When
        viewModel.onEvent(HomePageEvents.LoadPage)
        advanceUntilIdle()


        // Then
        val loadingState = viewModel.uiStateFlow.value.isLoading
        println("State update: ${viewModel.uiStateFlow.value}")
        assertThat(loadingState).isEqualTo(errorMessage)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `LoadPage Loading Posts (it only emits false it is starter) scenario`() = runTest(testDispatcher.main) {
        coEvery { mockGetStoryUseCase() } returns flowOf(
            ResultWrapper.Success(getFakeStory())
        )
        coEvery { mockGetPostsUseCase() } returns flowOf(
            ResultWrapper.Loading(false)
        )
        //Get
        val errorMessage = false

        // When
        viewModel.onEvent(HomePageEvents.LoadPage)
        advanceUntilIdle()


        // Then
        val loadingState = viewModel.uiStateFlow.value.isLoading
        println("State update: ${viewModel.uiStateFlow.value}")
        assertThat(loadingState).isEqualTo(errorMessage)
    }
}