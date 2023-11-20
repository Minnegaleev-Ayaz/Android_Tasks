package com.example.android_tasks

import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.base.BaseActivity
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.databinding.ActivityMainBinding
import com.example.android_tasks.ui.fragments.CorutinesSettingsFragment
import com.example.android_tasks.ui.fragments.NotificationFragment
import com.example.android_tasks.ui.fragments.NotifySettingsFragment
import com.example.android_tasks.utils.ActionType
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity() {
    override val fragmentContainerId:Int = R.id.fragment_container_view
    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val navHost = supportFragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment
        navHost.navController.navInflater.inflate(R.navigation.main_graph).setStartDestination(R.id.notificationFragment)
        val navController = navHost.navController
        NavigationUI.setupWithNavController(viewBinding.menuBnv,navHost.navController)*/
        val navHost = supportFragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment
        findViewById<BottomNavigationView>(R.id.menu_bnv)?.let { bottomNav ->
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                  R.id.notificationFragment -> {
                        goToScreen(ActionType.REPLACE,NotificationFragment(),null,true)
                        true
                    }

                    R.id.notifySettingsFragment -> {
                        goToScreen(ActionType.REPLACE,NotifySettingsFragment(),null,true)
                        true
                    }

                    else -> {
                        goToScreen(ActionType.REPLACE, CorutinesSettingsFragment(),null,true)
                        true
                    }
               }
            }
            bottomNav.setOnItemReselectedListener {}
        }
        requestPermission()
    }

    override fun goToScreen(
        actionType: ActionType,
        destination: BaseFragment,
        tag: String?,
        isAddToBackStack: Boolean,
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
    fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), NOTIFICATION_REQUEST_CODE)
    }
    companion object{
        private const val NOTIFICATION_REQUEST_CODE=12101
    }
}