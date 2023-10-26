package com.example.android_tasks.Models

data class Answer(
    val text:String,
    var isChecked: Boolean=false

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Answer

        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }
}
