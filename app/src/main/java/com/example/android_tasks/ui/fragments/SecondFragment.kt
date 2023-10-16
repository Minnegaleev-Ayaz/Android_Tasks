package com.example.android_tasks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_tasks.base.BaseActivity
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.utils.ActionType
import com.example.android_tasks.utils.ParamsKey
import com.example.androifgh.R
import com.example.androifgh.databinding.FragmentSecondBinding

class SecondFragment : BaseFragment(R.layout.fragment_second) {
    private var _viewBinding: FragmentSecondBinding? = null
    private val viewBinding: FragmentSecondBinding
        get() = _viewBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentSecondBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = arguments?.getString(ParamsKey.MESSAGE_KEY) ?:""
        with(viewBinding) {
            if (text.isNotEmpty()) {
                titleTv.text = text
            } else {
                titleTv.text = "Второй экран"
            }

            btnThird.setOnClickListener {
                parentFragmentManager.popBackStack()
                (requireActivity() as BaseActivity)?.goToScreen(
                    actionType = ActionType.REPLACE,
                    destination = ThirdFragment.newInstance(text) ,
                    tag = ParamsKey.THIRD_FRAGMENT_TAG,
                    isAddToBackStack = true
                )
            }

            btnFirst.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
    companion object{
        fun newInstance(message: String) = SecondFragment().apply {
            arguments = Bundle().apply {
                putString(ParamsKey.MESSAGE_KEY,message)
            }
        }
    }

}