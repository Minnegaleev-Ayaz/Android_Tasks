package com.example.hw6.ui

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.MainActivity
import com.example.android_tasks.R
import com.example.android_tasks.databinding.FragmentGrettingBinding
import com.example.hw6.utils.ActionType
import com.example.hw6.utils.ParamsKey

class GreetingFragment :BaseFragment(R.layout.fragment_gretting) {
    private val viewBinding:FragmentGrettingBinding by viewBinding(FragmentGrettingBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            transferBtn.setOnClickListener {
                (requireActivity() as MainActivity)?.goToScreen(
                    ActionType.REPLACE,
                    ShowFragment.newInstance(countEt.text.toString().toInt()),
                )
            }
        }
    }
    companion object{
        const val GREETING_FRAGMENT_TAG="GREETING_FRAGMENT_TAG"
        fun newInstance(count: Int) = GreetingFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.COUNT_KEY, count)
            }
        }
    }
}