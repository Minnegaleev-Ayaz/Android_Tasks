package com.example.android_tasks.Base


import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layout: Int): Fragment(layout) {
}