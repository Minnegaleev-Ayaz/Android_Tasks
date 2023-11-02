package com.example.android_tasks.Base

import androidx.appcompat.app.AppCompatActivity
import com.example.android_tasks.Utils.ActionType

abstract class BaseActivity:AppCompatActivity() {
    protected abstract val fragmentContainerId:Int
    abstract fun goToScreen(
        actionType: ActionType,
        destination: BaseFragment,
        tag: String? = null,
        isAddToBackStack: Boolean = true,
    )
}