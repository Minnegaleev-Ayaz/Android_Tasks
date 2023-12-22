package com.example.hw6.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.R
import com.example.hw6.adapter.CollageAdapter
import com.example.android_tasks.databinding.FragmentShowBinding
import com.example.hw6.utils.ParamsKey
import com.example.hw6.utils.SchemeGenerator


class ShowFragment:BaseFragment(R.layout.fragment_show) {
    private val viewBinding:FragmentShowBinding by viewBinding(FragmentShowBinding::bind)
    private var collageAdapter:CollageAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val count = arguments?.getInt(ParamsKey.COUNT_KEY)!!.toInt()
        initRecyclerView(count)

    }
    private fun initRecyclerView(count:Int){
        with(viewBinding){
            val mutableList: MutableList<Int> = SchemeGenerator.generateList(count)
            collageAdapter = CollageAdapter(mutableList)
            /*val gridLayoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )*/
            val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            /*gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                    if (position%6==0 ) 2
                    else 1
            }*/
            layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            schemaRv.layoutManager = layoutManager
            schemaRv.adapter = collageAdapter

            schemaRv.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
                    val position = layoutParams.absoluteAdapterPosition
                    if (mutableList[position]==3) {
                        layoutParams.isFullSpan = true
                    }
                }
            })
            schemaRv.adapter = collageAdapter
            /*schemaRv.addItemDecoration(GridSpacingItemDecoration(2,8))*/

        }
    }
    companion object {
        const val COLLAGE_FRAGMENT_TAG = "COLLAGE_FRAGMENT_TAG"
        fun newInstance(count: Int) = ShowFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.COUNT_KEY, count)
            }
        }
    }
}