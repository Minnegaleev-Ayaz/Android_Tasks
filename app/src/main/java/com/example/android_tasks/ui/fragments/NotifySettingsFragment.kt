package com.example.android_tasks.ui.fragments

import android.app.Notification
import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.R
import com.example.android_tasks.databinding.FragmentNotifySettingsBinding
import com.example.android_tasks.utils.NotificationCurrentConfig

class NotifySettingsFragment : BaseFragment(R.layout.fragment_notify_settings) {
    private val viewBinding: FragmentNotifySettingsBinding by viewBinding(
        FragmentNotifySettingsBinding::bind
    )
    var notificationSettingConfig = NotificationCurrentConfig.conf
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrentSettings()
        with(viewBinding) {
            var selectedImportance: String = ""
            notifyImportanceRg.setOnCheckedChangeListener { group, checkedId ->
                val radioButton: View? = notifyImportanceRg.findViewById(checkedId)
                if (radioButton is RadioButton) {
                    selectedImportance = radioButton.text.toString()
                }
                when (selectedImportance) {
                    getString(R.string.Urgent) -> {
                        NotificationCurrentConfig.conf.importance =
                            NotificationManager.IMPORTANCE_HIGH
                    }

                    getString(R.string.High) -> {
                        NotificationCurrentConfig.conf.importance =
                            NotificationManager.IMPORTANCE_DEFAULT
                    }

                    getString(R.string.Medium) -> {
                        NotificationCurrentConfig.conf.importance =
                            NotificationManager.IMPORTANCE_LOW
                    }

                    else -> AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.Error))
                        .setMessage(getString(R.string.ErrorBody))
                        .show()
                }
            }

            var selectedVisibility = ""
            notifyPrivacyRg.setOnCheckedChangeListener { group, checkedId ->
                val radioButton: View? = notifyPrivacyRg.findViewById(checkedId)
                if (radioButton is RadioButton) {
                    selectedVisibility = radioButton.text.toString()
                }
                when (selectedVisibility) {
                    getString(R.string.Public) -> {
                        NotificationCurrentConfig.conf.visibility =
                            Notification.VISIBILITY_PUBLIC
                    }

                    getString(R.string.Secret) -> {
                        NotificationCurrentConfig.conf.visibility =
                            Notification.VISIBILITY_SECRET
                    }

                    getString(R.string.Private) -> {
                        NotificationCurrentConfig.conf.visibility =
                            Notification.VISIBILITY_PRIVATE
                    }

                    else -> AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.Error))
                        .setMessage(getString(R.string.ErrorBody))
                        .show()
                }
            }

            var selectedDetail = ""
            detailedTextRg.setOnCheckedChangeListener { group, checkedId ->
                val radioButton: View? = detailedTextRg.findViewById(checkedId)
                if (radioButton is RadioButton) {
                    selectedDetail = radioButton.text.toString()
                }
                when (selectedDetail) {
                    getString(R.string.Yes) -> {
                        NotificationCurrentConfig.conf.detailed = true
                    }

                    getString(R.string.No) -> {
                        NotificationCurrentConfig.conf.detailed = false
                    }

                    else -> AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.Error))
                        .setMessage(getString(R.string.ErrorBody))
                        .show()
                }
            }

            var selectedAdditional = ""
            additionalActionRg.setOnCheckedChangeListener { group, checkedId ->
                val radioButton: View? = additionalActionRg.findViewById(checkedId)
                if (radioButton is RadioButton) {
                    selectedAdditional = radioButton.text.toString()
                }
                when (selectedAdditional) {
                    getString(R.string.Yes) -> {
                        NotificationCurrentConfig.conf.additional_action = true
                    }

                    getString(R.string.No) -> {
                        NotificationCurrentConfig.conf.additional_action = false
                    }

                    else -> AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.Error))
                        .setMessage(getString(R.string.ErrorBody))
                        .show()
                }
            }
        }
    }

    companion object {
        fun newInstance(message: String) = NotifySettingsFragment().apply {
            arguments = Bundle().apply {
                putString("123", message)
            }
        }
    }

    fun setCurrentSettings() {
        with(viewBinding) {
            when (notificationSettingConfig.importance) {
                NotificationManager.IMPORTANCE_HIGH -> {
                    urgentRb.isChecked = true
                }

                NotificationManager.IMPORTANCE_DEFAULT -> {
                    highRb.isChecked = true
                }

                else -> {
                    mediumRb.isChecked = true
                }
            }
            when (notificationSettingConfig.visibility) {
                Notification.VISIBILITY_PUBLIC -> {
                    publicRb.isChecked = true
                }

                Notification.VISIBILITY_SECRET -> {
                    secretRb.isChecked = true
                }

                else -> {
                    privateRb.isChecked = true
                }
            }
            if (notificationSettingConfig.detailed) {
                detYesRb.isChecked = true
            } else {
                detNoRb.isChecked = true
            }
            if (notificationSettingConfig.additional_action) {
                addYesRb.isChecked = true
            } else {
                addNoRb.isChecked = true
            }
        }
    }
}