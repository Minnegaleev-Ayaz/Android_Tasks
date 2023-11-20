package com.example.android_tasks.ui.fragments

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.R
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.databinding.FragmentCorutinesSettingsBinding

class CorutinesSettingsFragment:BaseFragment(R.layout.fragment_corutines_settings) {
    private val viewBinding: FragmentCorutinesSettingsBinding by viewBinding(FragmentCorutinesSettingsBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){}
    }

}