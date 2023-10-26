package com.example.android_tasks.Models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.text.FieldPosition

data class Question(
    val text: String?,
    val answers:MutableList<Answer>,
    val position: Int
):Serializable{
}