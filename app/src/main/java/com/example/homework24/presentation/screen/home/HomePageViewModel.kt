package com.example.homework24.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.usecase.GetPostsUseCase
import com.example.homework24.domain.usecase.GetStoryUseCase
import com.example.homework24.presentation.event.HomePageEvents
import com.example.homework24.presentation.mappers.toHomePageItem
import com.example.homework24.presentation.mappers.toUI
import com.example.homework24.presentation.state.HomePageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel@Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getStoryUseCase: GetStoryUseCase
):ViewModel() {

    private val _uiStateFlow = MutableStateFlow(HomePageState())
    val uiStateFlow : StateFlow<HomePageState> = _uiStateFlow

    private val oneTimeEventChannel = Channel<String>()
    val oneTimeEventFlow = oneTimeEventChannel.receiveAsFlow() //takes as a hot flow (not like stateFlow,but like a sharedFlow)


    private val _navigationEventFlow = MutableSharedFlow<HomePageNavigationEvent>()
    val navigationEventFlow get() = _navigationEventFlow.asSharedFlow()


    init {
        onEvent(HomePageEvents.LoadPage)
    }

    fun onEvent(event: HomePageEvents){
        when(event){
            HomePageEvents.LoadPage -> loadPage()
            is HomePageEvents.OpenDetailsPage -> emitNavigationToDetailsPageEvent(event.id)
        }
    }

    private fun emitNavigationToDetailsPageEvent(id:Int) = viewModelScope.launch {
        _navigationEventFlow.emit(HomePageNavigationEvent.NavigateToDetails(id))
    }

    private fun loadPage(){
        viewModelScope.launch(Dispatchers.IO) {

            getStoryUseCase().collect(){result->
                when(result){
                    is ResultWrapper.Error -> {
                        oneTimeEventChannel.send(result.errorMessage?:"")
                    }
                    is ResultWrapper.Loading -> {
                        if(result.loading)_uiStateFlow.update {
                            it.copy(isLoading = result.loading)
                        }
                    }
                    is ResultWrapper.Success -> {
                        _uiStateFlow.update {
                            val storyList = result.data!!.map {story->
                                story.toUI()
                            }.toHomePageItem()
                            it.copy(isSuccess = listOf(storyList))
                        }
                    }
                }
            }

            getPostsUseCase().collect(){result->
                when(result){
                    is ResultWrapper.Error -> {
                        oneTimeEventChannel.send(result.errorMessage?:"")
                    }
                    is ResultWrapper.Loading -> {
                        if(!result.loading)_uiStateFlow.update {
                            it.copy(isLoading = result.loading)
                        }
                    }
                    is ResultWrapper.Success -> {
                        _uiStateFlow.update {
                            val list = result.data!!.map {post->
                                post.toUI().toHomePageItem()
                            }
                            val newList = _uiStateFlow.value.isSuccess?.plus(list)
                            it.copy(isSuccess = newList)
                        }
                    }
                }
            }


        }
    }
}

sealed interface HomePageNavigationEvent{
    data class NavigateToDetails(val id:Int):HomePageNavigationEvent
}