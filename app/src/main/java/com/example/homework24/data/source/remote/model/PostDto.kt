package com.example.homework24.data.source.remote.model

import com.squareup.moshi.Json

data class PostDto(
    val id:Int = 0,
    val images:List<String>? = null,
    val title:String= "",
    val comments:Int=0,
    val likes:Int=0,
    @Json(name = "share_content")
    val shareContent:String="",
    val owner: OwnerDto = OwnerDto()
) {
}