package com.example.android_tasks

import android.os.Bundle
import com.example.android_tasks.base.BaseActivity
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.ui.fragments.FirstFragment
import com.example.android_tasks.utils.ActionType
import com.example.android_tasks.utils.ParamsKey
import com.example.androifgh.R

class MainActivity: BaseActivity() {
    override val fragmentContainerId: Int = R.id.main_activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    fragmentContainerId,
                    FirstFragment.newInstance("message"),
                    ParamsKey.FIRST_FRAGMENT_TAG,
                )
                .commit()
        }
    }
    override fun goToScreen(
        actionType: ActionType,
        destination: BaseFragment,
        tag: String?,
        isAddToBackStack: Boolean
    ) {
        supportFragmentManager.beginTransaction().apply {
            when (actionType) {
                ActionType.ADD -> {
                    this.add(fragmentContainerId, destination, tag)
                }

                ActionType.REPLACE -> {
                    this.replace(fragmentContainerId, destination, tag)
                }

                ActionType.REMOVE -> {
                    this.remove(destination)
                }

                else -> Unit
            }
            if (isAddToBackStack) {
                this.addToBackStack(null)
            }
        }.commit()
    }
}