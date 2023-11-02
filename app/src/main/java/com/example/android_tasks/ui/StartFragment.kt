package com.example.android_tasks.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.Base.BaseActivity
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.MainActivity
import com.example.android_tasks.R
import com.example.android_tasks.Utils.ActionType
import com.example.android_tasks.Utils.ParamsKey
import com.example.android_tasks.databinding.FragmentStartBinding


class StartFragment:BaseFragment(R.layout.fragment_start){
    private val viewBinding:FragmentStartBinding by viewBinding(FragmentStartBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var flag: Boolean = false
        with(viewBinding){
            countEt.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (countEt.text!!.isNotEmpty()){
                        if (countEt.text.toString().toInt()>45){
                            flag=false
                            countEt.error = "out of range"
                        }else{
                            flag=true
                        }
                    }else{
                        countEt.error = "Empty"
                        flag=false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if(flag){
                        countEt.isEnabled=true
                    }
                }
            })
            nexBtn.setOnClickListener {
                (requireActivity() as MainActivity).goToScreen(
                    ActionType.REPLACE,
                    NewsFragment.newInstance(countEt.text.toString().toInt()),
                    START_FRAGMENT_TAG
                )
            }
        }
    }
    companion object{
        const val START_FRAGMENT_TAG="START_FRAGMENT_TAG"
        fun newInstance(count: Int) = StartFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.COUNT_KEY, count)
            }
        }
    }
}