package com.example.android_tasks.base

import androidx.appcompat.app.AppCompatActivity
import com.example.android_tasks.utils.ActionType

abstract class BaseActivity: AppCompatActivity() {

    protected abstract val fragmentContainerId: Int
    abstract fun goToScreen(
        actionType: ActionType,
        destination: BaseFragment,
        tag: String? = null,
        isAddToBackStack: Boolean = true,
    )
}