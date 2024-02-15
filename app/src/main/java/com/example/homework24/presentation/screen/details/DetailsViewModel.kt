package com.example.homework24.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework24.domain.resoult_wraper.ResultWrapper
import com.example.homework24.domain.usecase.GetPostDetailsUseCase
import com.example.homework24.presentation.event.DetailsPageEvents
import com.example.homework24.presentation.mappers.toUI
import com.example.homework24.presentation.state.DetailsPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase:GetPostDetailsUseCase
):ViewModel() {

    private val _uiStateFlow = MutableStateFlow(DetailsPageState())
    val uiStateFlow : StateFlow<DetailsPageState> = _uiStateFlow

    private val oneTimeEventChannel = Channel<String>()
    val oneTimeEventFlow = oneTimeEventChannel.receiveAsFlow()


    fun onEvent(event: DetailsPageEvents){
        when(event){
            is DetailsPageEvents.LoadPage -> loadDetails(event.id)
        }
    }

    private fun loadDetails(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            getPostDetailsUseCase(id).collect{
                    result->
                when(result){
                    is ResultWrapper.Error -> {
                        oneTimeEventChannel.send(result.errorMessage?:"")
                    }
                    is ResultWrapper.Loading -> {
                        _uiStateFlow.update {
                            it.copy(isLoading = result.loading)
                        }
                    }
                    is ResultWrapper.Success -> {
                        _uiStateFlow.update {
                            val post = result.data!!.toUI()
                            it.copy(isSuccess = post)
                        }
                    }
                }
            }
        }
    }
}