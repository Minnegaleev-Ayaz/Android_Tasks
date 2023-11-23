package com.example.android_tasks.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.android_tasks.MainActivity
import com.example.android_tasks.R
import com.example.android_tasks.ui.fragments.NotifySettingsFragment

class NotificationsHandler {
    @SuppressLint("SuspiciousIndentation")
    fun createNotification(ctx: Context, title:String, desc:String, id: Int) {
        (ctx.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.let { manager ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    "first_notification_channel",
                    "Канал уведомлений",
                    NotificationCurrentConfig.conf.importance
                )
                println(channel.importance)
                manager.createNotificationChannel(channel)
            }

            val intent = Intent(ctx, MainActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(
                ctx,
                101,
                intent,
                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )

            var notification = NotificationCompat.Builder(ctx, "first_notification_channel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(desc)
                .setAutoCancel(true)
                .setVisibility(NotificationCurrentConfig.conf.visibility)
                .setContentIntent(pendingIntent)
                if (NotificationCurrentConfig.conf.detailed){
                    notification.setStyle(NotificationCompat.BigTextStyle().bigText(desc))
                }
                if(NotificationCurrentConfig.conf.additional_action){
                    val firstActionIntent = intent
                    firstActionIntent.putExtra(ACTION_KEY,12)
                    val firstActionPendingIntent = (PendingIntent.getActivity(
                        ctx,102,firstActionIntent,PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
                    ))
                    val secondActionIntent = intent
                    secondActionIntent.putExtra(ACTION_KEY,23)
                    firstActionPendingIntent
                    val secondActionPendingIntent = PendingIntent.getActivity(
                        ctx,103,secondActionIntent,PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
                    )
                    notification.addAction(R.drawable.ic_launcher_foreground,"Действие 1",(firstActionPendingIntent))
                    notification.addAction(R.drawable.ic_launcher_foreground,"Действие 2",secondActionPendingIntent)
                }
            manager.notify(id, notification.build())
        }
    }
    companion object{
        private const val ACTION_KEY = "ACTION_KEY"

    }
}