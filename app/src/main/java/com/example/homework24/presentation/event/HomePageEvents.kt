package com.example.homework24.presentation.event

sealed class HomePageEvents {
    data object LoadPage:HomePageEvents()
    data class OpenDetailsPage(val id:Int):HomePageEvents()
}