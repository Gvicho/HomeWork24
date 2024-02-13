package com.example.homework24.presentation.model

data class PostUI(
    val id:Int,
    val images:List<String>,
    val title:String,
    val comments:Int,
    val likes:Int,
    val shareContent:String,
    val owner:OwnerUI
) {
}