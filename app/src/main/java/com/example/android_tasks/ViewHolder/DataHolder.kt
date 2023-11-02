package com.example.android_tasks.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.android_tasks.Models.Data
import com.example.android_tasks.Models.Item
import com.example.android_tasks.databinding.ItemDataBinding

class DataHolder(
    private val viewBinding : ItemDataBinding
): RecyclerView.ViewHolder(viewBinding.root) {
    fun bindItem(item: Item){
        with(viewBinding){
            dateTv.text = item.data?.date
        }
    }
}