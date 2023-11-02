package com.example.android_tasks.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_tasks.Models.Item
import com.example.android_tasks.Models.News
import com.example.android_tasks.Utils.ItemTypes
import com.example.android_tasks.ViewHolder.ButtonHolder
import com.example.android_tasks.ViewHolder.DataHolder
import com.example.android_tasks.ViewHolder.NewsHolder
import com.example.android_tasks.databinding.ItemButtonBinding
import com.example.android_tasks.databinding.ItemDataBinding
import com.example.android_tasks.databinding.ItemNewsBinding
import com.kpfu.itis.android_inception_23.adapter.diffutil.NewsDiffUtil

class NewsAdapter(
    private val fragmentManager:FragmentManager,
    private val onNewsClicked: ((Item) -> Unit),
    private val onLikeClicked: ((Int, Item) -> Unit),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var newsList = mutableListOf<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                NewsHolder(
                    viewBinding = ItemNewsBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ),
                    onNewsClicked,
                    onLikeClicked
                )
            }

            2 -> {
                DataHolder(
                    viewBinding = ItemDataBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }

            else -> {
                ButtonHolder(
                    viewBinding = ItemButtonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    ), this, fragmentManager
                )
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ButtonHolder -> {
                holder.bindItem()
            }

            is NewsHolder -> {
                newsList[position].let { holder.bindItem(it) }
            }

            is DataHolder -> {
                newsList[position].let { holder.bindItem(it) }
            }
        }
    }

    override fun getItemCount(): Int = newsList.size
    override fun getItemViewType(position: Int): Int {
        val currentItem = newsList[position]
        return when {
            currentItem.isButton -> ItemTypes.BUTTON.number
            currentItem.isData -> ItemTypes.DATA.number
            else -> ItemTypes.NEWS.number
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<Item>) {
        val diff = NewsDiffUtil(oldItemsList = newsList, newItemsList = list)
        val diffResult = DiffUtil.calculateDiff(diff)
        newsList.clear()
        newsList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateItem(position: Int, item: Item) {
        this.newsList[position] = item
        notifyItemChanged(position, item.news?.isFav)

    }

}