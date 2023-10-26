package com.example.android_tasks

import android.os.Bundle
import com.example.android_tasks.Base.BaseActivity
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.ui_fragments.OprosnikFragment
import com.example.android_tasks.ui_fragments.StartFragment
import com.example.android_tasks.utils.ActionType

class MainActivity : BaseActivity(){
    override val fragmentContainerId: Int = R.id.main_activity_container
    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    fragmentContainerId,
                    StartFragment(),
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