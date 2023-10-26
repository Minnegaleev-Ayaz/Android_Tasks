package com.example.android_tasks.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_tasks.ui_holders.AnswerHolder
import com.example.android_tasks.Models.Answer
import com.example.android_tasks.Models.Question
import com.example.android_tasks.databinding.AnswerItemBinding

class AnswerAdapter(
    val items: MutableList<Answer>,
    private val onItemChecked: (Int)->Unit,
    private val onRootClicked: (Int)->Unit,
) : RecyclerView.Adapter<AnswerHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        return AnswerHolder(viewBinding = AnswerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),
            onItemChecked = onItemChecked,
            onRootClicked = onRootClicked,)
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        holder.bindItem(item = items[position])
    }
    override fun getItemCount(): Int = items.size
}