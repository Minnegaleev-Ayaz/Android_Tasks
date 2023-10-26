package com.example.android_tasks.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.Models.Answer
import com.example.android_tasks.Models.Question
import com.example.android_tasks.ui_fragments.AnswerFragment

class OprosnikAdapter(private val answers:MutableList<Question> ,manager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(manager,lifecycle) {
    override fun getItemCount(): Int = answers.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun createFragment(position: Int): BaseFragment {
        return AnswerFragment.newInstance(position, answers[position])
    }
}