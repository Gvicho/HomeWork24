package com.example.homework24.presentation.state

import com.example.homework24.presentation.model.HomePageItemUI

data class HomePageState(
    val isLoading:Boolean = false,
    val isSuccess:List<HomePageItemUI>? = null
)