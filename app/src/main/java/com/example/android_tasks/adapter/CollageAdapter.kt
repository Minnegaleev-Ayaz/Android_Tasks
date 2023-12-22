package com.example.hw6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_tasks.databinding.ItemHorizontalRectangleBinding
import com.example.android_tasks.databinding.ItemLittleSquareBinding
import com.example.android_tasks.databinding.ItemVerticalRectangleBinding
import com.example.hw6.holder.HorizontalRectangleViewHolder
import com.example.hw6.holder.LittleSquareViewHolder
import com.example.hw6.holder.VerticalRectangleViewHolder
import com.example.hw6.model.CollageElementTypes

class CollageAdapter (
    val items:MutableList<Int>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1->{
                LittleSquareViewHolder(viewBinding = ItemLittleSquareBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            2->{
                VerticalRectangleViewHolder(viewBinding = ItemVerticalRectangleBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false))
            }
            else->{
                HorizontalRectangleViewHolder(viewBinding = ItemHorizontalRectangleBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LittleSquareViewHolder ->{
            }
            is VerticalRectangleViewHolder ->{
            }
            is HorizontalRectangleViewHolder ->{
                val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams

                if (items[position]==3) {
                    layoutParams.isFullSpan = true
                    layoutParams.width = (holder.itemView.resources.displayMetrics.widthPixels / 2.5).toInt()
                } else {
                    layoutParams.isFullSpan = false
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position]
    }
}