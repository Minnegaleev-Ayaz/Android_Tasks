package com.example.android_tasks.Utils

import com.example.android_tasks.Data.NewsRepository
import com.example.android_tasks.Models.Item
import com.example.android_tasks.Models.News
import kotlin.random.Random

object ItemGenerator {
    var currentList = mutableListOf<Item>()
    var bottomList = mutableListOf<Item>()
    private var num: Int = 0
    fun itemGeneration(count: Int) {
        if (currentList.isEmpty() || num != count) {
            val res_list = arrayListOf<Item>(
                Item(null, null, false, true)
            )
            val news_list = NewsGenerator.newsGeneration(count)
            val data_list = DataGenerator.dataGeneration((count - 1) / 8 + 1)
            val realCount = data_list.size + count
            var data_i = 0
            var news_i = 0
            for (i in 1..realCount) {
                if (i == 1 || i == 10 || i == 19 || i == 28 || i == 37 || i == 46) {
                    res_list.add(Item(null, data_list[data_i], true, false))
                    data_i++
                } else {
                    res_list.add(Item(news_list[news_i], null, false, false))
                    news_i++
                }
            }
            num = count
            currentList = res_list
        }

    }

    fun bottomSheetGeneration(number: Int) {
        val news_list = NewsGenerator.newsGeneration(number)
        for (i in 0..number-1) {
            currentList.add(Item(news_list[i], null, false, false))

        }
    }
}
