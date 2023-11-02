package com.example.android_tasks.Models

import java.io.Serializable

data class News(
    val id:Int,
    val title:String,
    val image:Int,
    var isFav: Boolean,
    val desc:String
)