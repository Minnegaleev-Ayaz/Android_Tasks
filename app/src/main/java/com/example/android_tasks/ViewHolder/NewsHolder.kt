package com.example.android_tasks.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.Models.Item
import com.example.android_tasks.Models.News
import com.example.android_tasks.R
import com.example.android_tasks.databinding.ItemNewsBinding

class NewsHolder(
    private val viewBinding: ItemNewsBinding,
    private val onNewsClicked: ((Item) -> Unit),
    private val onLikeClicked: ((Int, Item) -> Unit),
) : RecyclerView.ViewHolder(viewBinding.root) {
    private var item: Item? = null

    init {
        viewBinding.root.setOnClickListener {
            this.item?.let(onNewsClicked)
        }
        viewBinding.favIb.setOnClickListener {
            this.item?.news.let {
                val data = it?.copy(isFav = !it.isFav)
                onLikeClicked(adapterPosition, Item(data,null,false,false))
            }
        }
    }

    fun bindItem(item: Item) {
        this.item = item
        with(viewBinding) {
            newsTv.text = item.news?.title
            item.news?.image?.let { res ->
                newsIv.setImageResource(res)
            }
            item.news?.isFav?.let { changeLikeBtnStatus(isChecked = it) }
        }
    }

    fun changeLikeBtnStatus(isChecked: Boolean) {
        val likeDrawable = if (isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border
        viewBinding.favIb.setImageResource(likeDrawable)
    }
}

