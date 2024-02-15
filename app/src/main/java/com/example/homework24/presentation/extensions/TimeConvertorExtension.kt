package com.example.homework24.presentation.extensions

import com.example.homework24.presentation.extensions.TimeConst.simpleDateFormat
import java.text.SimpleDateFormat
import java.util.Locale

object TimeConst{
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)
}

fun Long.convertToDateString():String{
    return simpleDateFormat.format(this * 1000L)
}