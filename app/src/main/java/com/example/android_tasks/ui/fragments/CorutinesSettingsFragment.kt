package com.example.android_tasks.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.MainActivity
import com.example.android_tasks.R
import com.example.android_tasks.base.BaseFragment
import com.example.android_tasks.databinding.FragmentCorutinesSettingsBinding
import com.example.android_tasks.utils.NotificationsHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException

class CorutinesSettingsFragment:BaseFragment(R.layout.fragment_corutines_settings) {
    private val viewBinding: FragmentCorutinesSettingsBinding by viewBinding(FragmentCorutinesSettingsBinding::bind)
    private var job: Job? = null
    private var coroutinesFinish= 0
    private var stopOnBackground = false
    private var current_corutine=0
    private val notificationHandler = NotificationsHandler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var corutinesNumber:Int =1
        with(viewBinding){
            countSb.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    curCntTv.text = getString(R.string.value,p1)
                    corutinesNumber = p1
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })
            startCorutineBtn.setOnClickListener {

               initializationCorutines(corutinesNumber,asyncCb.isActivated,stopOnBackCb.isActivated)
            }
        }
    }
    fun initializationCorutines(count:Int,async:Boolean,stopOnBack:Boolean){
        job = lifecycleScope.launch {
            withContext(Dispatchers.IO){
                repeat(count){
                    if (async){
                        launch { startCorutine() }
                    }else
                    {
                        startCorutine()
                    }
                }
            }
        }.also {
            it.invokeOnCompletion { cause ->
                if (cause == null) {
                    context?.let { it1 ->
                        notificationHandler.createNotification(
                            it1,
                            getString(R.string.succesful),
                            getString(R.string.corutinesIsFinished),
                            5
                        )
                    }
                } else if (cause is CancellationException) {
                    Log.e(MainActivity.LOG_TAG,getString(R.string.corutinesFailed))
                }
                job = null
            }
        }
    }
    private suspend fun  startCorutine(){
        delay(1000)
        Log.e(MainActivity.LOG_TAG,getString(R.string.corutine_finished,current_corutine))
        current_corutine++
    }
    override fun onStop() {
        if (stopOnBackground) {
            job?.cancel(
                CancellationException(context?.getString(R.string.stopOnBackEX))
            )
        }
        super.onStop()
    }
}