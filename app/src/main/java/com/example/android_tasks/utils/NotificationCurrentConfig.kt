package com.example.android_tasks.utils

import android.app.Notification
import android.app.NotificationManager
import com.example.android_tasks.Models.NotificationSettingConfig

object NotificationCurrentConfig {
    var conf: NotificationSettingConfig = NotificationSettingConfig(
        NotificationManager.IMPORTANCE_LOW,
        Notification.VISIBILITY_PUBLIC,
        false,
        false)
}