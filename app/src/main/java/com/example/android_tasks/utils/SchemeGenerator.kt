package com.example.hw6.utils

import android.util.Log
import kotlin.math.log

object SchemeGenerator {
    fun generateList(count: Int): MutableList<Int> {
        var itemCount = count
        var list: MutableList<Int> = mutableListOf()

        if (itemCount > 6) {
            while ((itemCount.div(6)) >= 1) {
                Log.e("qwertyu",count.toString())
                itemCount -= 6
                list = list.plus(mutableListOf(3, 1, 2, 2, 1,3)).toMutableList()
            }
        }
        when (itemCount) {
            1 -> {
                list.add(3)
            }
            2 -> {
                list = list.plus(mutableListOf(3,1,1)).toMutableList()
            }
            3 -> { list = list.plus(mutableListOf(3,2,2)).toMutableList()}
            4 -> {
                list = list.plus(mutableListOf(3,2,2,3)).toMutableList()
            }
            5->{
                list = list.plus(mutableListOf(3,1,2,2,1)).toMutableList()
            }
            6 -> {
                list = list.plus(mutableListOf(3,1,2,2,1,3)).toMutableList()
            }
        }

        return list.toMutableList()
    }
}