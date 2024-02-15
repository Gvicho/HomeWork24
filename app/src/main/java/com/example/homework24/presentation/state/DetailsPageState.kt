package com.example.homework24.presentation.state

import com.example.homework24.presentation.model.PostUI

data class DetailsPageState(
    val isLoading:Boolean = false,
    val isSuccess:PostUI? = null
) {
}