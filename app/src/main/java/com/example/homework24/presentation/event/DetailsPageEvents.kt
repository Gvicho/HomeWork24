package com.example.homework24.presentation.event

sealed class DetailsPageEvents {
    data class LoadPage(val id:Int):DetailsPageEvents()
}