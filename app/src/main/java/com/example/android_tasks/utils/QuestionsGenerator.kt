package com.example.android_tasks.utils

import com.example.android_tasks.Models.Answer
import com.example.android_tasks.Models.Question
import com.google.android.material.color.utilities.QuantizerWsmeans
import java.util.Random

import kotlin.time.measureTime

object QuestionsGenerator {
    var x=0
    fun generateQuestions(count: Int): MutableList<Question> {
        val list = mutableListOf<Question>()
        for (i in 0..count) {
            list.add(generateQuestion())
        }
        return list
    }

    private fun generateQuestion(): Question {
        val list = CompositorsData.list.random()
        val correctAns = list[1]
        var ansSet = mutableSetOf<Answer>((Answer(list[1], false)))
        var randomnum = Random().nextInt(16) + 2
        var i = 0
        while (i < randomnum) {
            ansSet.add(Answer(CompositorsData.list.random()[1], false))
            i++
        }
        x++
        return Question(list[0],ansSet.shuffled().toMutableList(),x)

    }

}

