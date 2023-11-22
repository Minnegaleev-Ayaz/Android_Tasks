package com.example.android_tasks.Models

data class NotificationSettingConfig(
    var importance:Int,
    var visibility:Int,
    var detailed:Boolean,
    var additional_action:Boolean
){
    override fun toString(): String {
        return importance.toString()+visibility.toString()+detailed.toString()+additional_action.toString()
    }
}