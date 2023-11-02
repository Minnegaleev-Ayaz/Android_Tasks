package com.example.android_tasks.ViewHolder

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_tasks.Adapter.NewsAdapter
import com.example.android_tasks.databinding.ItemButtonBinding
import com.example.android_tasks.ui.BottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ButtonHolder(
    private val viewBinding:ItemButtonBinding,
    private val adapter : NewsAdapter,
    private val fragmentManager: FragmentManager,
):RecyclerView.ViewHolder(viewBinding.root) {
    fun bindItem(){
       with(viewBinding){
           bsBtn.setOnClickListener {
               val bottomSheetFragment = BottomSheetFragment(adapter)
               bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
           }
       }
    }
}