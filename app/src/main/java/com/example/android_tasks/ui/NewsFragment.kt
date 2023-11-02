package com.example.android_tasks.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.Adapter.NewsAdapter
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.MainActivity
import com.example.android_tasks.Models.Item
import com.example.android_tasks.Models.News
import com.example.android_tasks.R
import com.example.android_tasks.Utils.ActionType
import com.example.android_tasks.Utils.ItemGenerator
import com.example.android_tasks.Utils.ParamsKey
import com.example.android_tasks.databinding.FragmentNewsBinding

class NewsFragment : BaseFragment(R.layout.fragment_news) {
    private val viewBinding: FragmentNewsBinding by viewBinding(FragmentNewsBinding::bind)

    private var newsAdapter: NewsAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
    }


    private fun initRecyclerview() {
        val count = arguments?.getInt(ParamsKey.COUNT_KEY)!!.toInt()
        newsAdapter = NewsAdapter(
            onNewsClicked = ::onNewsClicked,
            onLikeClicked = ::onLikeClicked,
            fragmentManager = parentFragmentManager
        )
        with(viewBinding) {
            val layoutManager = newsRv.layoutManager as GridLayoutManager
            if (count > 12) {
                layoutManager.spanCount = 2
                layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        if (position == 0 || position == 1 || position == 10 || position == 19 || position == 28 || position == 37 ) {
                            return 2
                        } else {
                            return 1
                        }
                    }
                }
            }
            ItemGenerator.itemGeneration(count)
            var itemList = ItemGenerator.currentList
            newsRv.adapter = newsAdapter
            newsAdapter?.setItems(itemList)
        }

    }

    private fun onNewsClicked(news: Item) {
        (requireActivity() as MainActivity)?.goToScreen(
            ActionType.REPLACE,
            DetailNewsFragment.newInstance(news.news!!.id),
            DetailNewsFragment.DETAIL_NEWS_FRAGMENT_TAG, true
        )
    }

    private fun onLikeClicked(position: Int, newsDataModel: Item) {
        newsAdapter?.updateItem(position, newsDataModel)
    }

    companion object {
        const val NEWS_FRAGMENT_TAG = "NEWS_FRAGMENT_TAG"
        fun newInstance(count: Int) = NewsFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.COUNT_KEY, count)
            }
        }
    }

}