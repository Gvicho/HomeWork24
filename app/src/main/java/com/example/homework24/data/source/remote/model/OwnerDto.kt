package com.example.homework24.data.source.remote.model

import com.squareup.moshi.Json

data class OwnerDto(
    @Json(name = "first_name")
    val firstName:String="",
    @Json(name = "last_name")
    val lastName:String="",
    val profile:String? = null,
    @Json(name = "post_date")
    val postDate:Long = 0L
) {
}