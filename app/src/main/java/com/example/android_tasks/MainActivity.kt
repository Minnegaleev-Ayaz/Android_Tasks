package com.example.android_tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.android_tasks.base.BaseActivity
import com.example.android_tasks.base.BaseFragment
import com.example.hw6.ui.GreetingFragment
import com.example.hw6.utils.ActionType

class MainActivity : BaseActivity() {
    override val fragmentContainerId: Int
        get() =R.id.fragment_container_view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(fragmentContainerId,GreetingFragment())
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

            }
            if (isAddToBackStack) {
                this.addToBackStack(null)
            }
        }.commit()
    }

}
