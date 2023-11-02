package com.example.android_tasks.Utils

import com.example.android_tasks.Data.DataRepository
import com.example.android_tasks.Data.NewsRepository
import com.example.android_tasks.Models.Data
import com.example.android_tasks.Models.News
import kotlin.random.Random

object DataGenerator {
    fun dataGeneration(count:Int):List<Data>{
        var array = DataRepository.list
        if (count > array.size) {
            throw IllegalArgumentException("Количество уникальных элементов (n) не может быть больше размера массива (m).")
        }

        val mutableArray = array.toMutableList()
        val result = mutableListOf<Data>()

        for (i in 0 until count) {
            if (mutableArray.isEmpty()) {
                break
            }
            val randomIndex = Random.nextInt(mutableArray.size)
            val randomElement = mutableArray[randomIndex]
            result.add(randomElement)
            mutableArray.removeAt(randomIndex)
        }

        return result
    }
}