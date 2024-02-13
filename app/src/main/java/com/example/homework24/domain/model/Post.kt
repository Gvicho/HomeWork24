package com.example.homework24.domain.model

data class Post(
    val id:Int,
    val images:List<String>,
    val title:String,
    val comments:Int,
    val likes:Int,
    val shareContent:String,
    val owner: Owner
) {
}