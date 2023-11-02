package com.kpfu.itis.android_inception_23.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.android_tasks.Models.Item

class NewsDiffUtil(
    private val oldItemsList: List<Item>,
    private val newItemsList: List<Item>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItemsList.size

    override fun getNewListSize(): Int = newItemsList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemsList[oldItemPosition]
        val newItem = newItemsList[newItemPosition]
        return oldItem.news?.id == newItem.news?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemsList[oldItemPosition]
        val newItem = newItemsList[newItemPosition]

        return (oldItem.news?.title == newItem.news?.title) &&
                (oldItem.news?.desc == newItem.news?.desc)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItemsList[oldItemPosition]
        val newItem = newItemsList[newItemPosition]

        return if (oldItem.news?.isFav != newItem.news?.isFav) {
            newItem.news?.isFav
        } else {
            super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}