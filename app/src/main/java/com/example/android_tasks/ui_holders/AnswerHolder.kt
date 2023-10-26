package com.example.android_tasks.ui_holders

import androidx.recyclerview.widget.RecyclerView
import com.example.android_tasks.Models.Answer
import com.example.android_tasks.databinding.AnswerItemBinding

class AnswerHolder(
    private val viewBinding: AnswerItemBinding,
    private val onItemChecked: (Int) -> Unit,
    private val onRootClicked: (Int) -> Unit,

    ) : RecyclerView.ViewHolder(viewBinding.root) {
    init {
        viewBinding.pointRb.setOnClickListener {
            onItemChecked?.invoke(adapterPosition)
        }
        viewBinding.root.setOnClickListener {
            onRootClicked?.invoke(adapterPosition)
        }
    }
    fun bindItem(item: Answer) {
        with(viewBinding) {
            textTv.text = item.text
            pointRb.isActivated = item.isChecked
            pointRb.isEnabled = !item.isChecked

            if (item.isChecked) {
                root.foreground = null
            }
        }
    }
}