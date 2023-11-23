package com.example.android_tasks.ui.fragments


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.R
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.databinding.FragmentNotificationBinding
import com.example.android_tasks.utils.NotificationsHandler


class NotificationFragment:BaseFragment(R.layout.fragment_notification) {
    private val viewBinding by viewBinding(FragmentNotificationBinding::bind)
    private val notificationHandler = NotificationsHandler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){

            sendNotifyBtn.setOnClickListener{
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.POST_NOTIFICATIONS,
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    if (requireActivity().shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                        AlertDialog.Builder(requireContext())
                            .setTitle(getString(R.string.permission_denied_pattern, "Уведомления"))
                            .setMessage(getString(R.string.permission_denined_first_time_desc))
                            .setPositiveButton(
                                getString(R.string.back)
                            ) { dialog, which ->
                            }.setNegativeButton(
                                getString(R.string.go_to_settings)){
                                    dialog,which ->
                                openApplicationSettings()
                                }

                            .show()
                    } else {
                        AlertDialog.Builder(requireContext())
                            .setTitle(getString(R.string.permission_denied_pattern, "Уведомления"))
                            .setMessage(getString(R.string.permission_denined_second_time_desc))
                            .setPositiveButton(
                                getString(R.string.go_to_settings)
                            ) { dialog, which ->
                                openApplicationSettings()

                            }
                            .show()
                    }
                } else{
                    notificationHandler.createNotification(requireContext(),titleEt.text.toString(),descvEt.text.toString(),1)
                    titleEt.text.clear()
                    descvEt.text.clear()
                }
            }
        }
    }

    companion object{
        private const val NOTIFICATION_REQUEST_CODE=12101
    }

    private fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:" + requireActivity().packageName)
        )
        startActivityForResult(appSettingsIntent, NOTIFICATION_REQUEST_CODE)
    }
}