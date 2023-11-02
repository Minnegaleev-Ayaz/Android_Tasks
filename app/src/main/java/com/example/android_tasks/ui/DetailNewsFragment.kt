package com.example.android_tasks.ui

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.Data.NewsRepository
import com.example.android_tasks.Models.Item
import com.example.android_tasks.Models.News
import com.example.android_tasks.R
import com.example.android_tasks.Utils.ParamsKey
import com.example.android_tasks.databinding.FragmentDetailNewsBinding

class DetailNewsFragment: BaseFragment(R.layout.fragment_detail_news) {
    private val viewBinding : FragmentDetailNewsBinding by viewBinding(FragmentDetailNewsBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val news:News = getInfo()
        with(viewBinding){
            titleTv.text = news.title
            descTv.text = news.desc
            imageIv.setImageResource(news.image)

        }
    }
    private fun getInfo(): News {
        val id = arguments?.getInt(ParamsKey.ITEM_KEY)
        return NewsRepository.list.single{it.id==id}
    }
    companion object{
        const val DETAIL_NEWS_FRAGMENT_TAG="DETAIL_NEWS_FRAGMENT_TAG"
        fun newInstance(id:Int) = DetailNewsFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.ITEM_KEY, id)
            }
        }
    }
}