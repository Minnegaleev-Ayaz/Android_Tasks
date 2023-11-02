package com.kpfu.itis.android_inception_23.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.android_tasks.Models.Item


class NewsDiffUtilItemCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.news?.id == newItem.news?.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.news?.isFav == newItem.news?.isFav
    }
}