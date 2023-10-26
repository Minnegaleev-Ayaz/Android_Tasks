package com.example.android_tasks.ui_fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.Base.BaseActivity
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.R
import com.example.android_tasks.databinding.FragmentStartBinding
import com.example.android_tasks.utils.ActionType
import com.example.android_tasks.utils.CompositorsData
import com.example.android_tasks.utils.ParamsKey

class StartFragment : BaseFragment(R.layout.fragment_start) {
    private val viewBinding: FragmentStartBinding by viewBinding(FragmentStartBinding::bind)
    private var phoneIsValid=false
    private var cntQisValid = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val number = arguments?.getInt("Key")
        with(viewBinding) {
            countIt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (countIt.text!!.isNotEmpty()){
                        if (countIt.text.toString().toInt()<5 ||countIt.text.toString().toInt()>CompositorsData.list.size){
                            cntQisValid=false
                            countIt.error = "out of range 4<n<${CompositorsData.list.size}"
                        }else{
                            cntQisValid=true
                        }
                    }else{
                        countIt.error = "Empty"
                        cntQisValid=false
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    possibletoOpen(cntQisValid,phoneIsValid)
                }
            })
            phoneEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val phoneNumber=phoneEditText.text.toString()
                    // Удаляем все символы, кроме цифр
                    val digitsOnly = phoneNumber.replace(Regex("[^\\d]"), "")

                    // Проверяем, что осталось 11 цифр (включая код страны +7)
                    if (digitsOnly.length != 11 || !digitsOnly.startsWith("79")) {
                        phoneEditText.error = "inccorrect number"
                    }else{
                        phoneIsValid=true
                    }

                }

                override fun afterTextChanged(s: Editable?) {
                    possibletoOpen(cntQisValid,phoneIsValid)
                }
            })
            nextBtn.setOnClickListener {
                (requireActivity() as BaseActivity)?.goToScreen(
                    actionType = ActionType.REPLACE,
                    OprosnikFragment.newInstance((countIt.text.toString().toInt() - 1)),
                    OprosnikFragment.OPROSNIK_FRAGMENT_TAG,true
                )
            }
        }
    }
    private fun possibletoOpen(count:Boolean,phone:Boolean){
        viewBinding.nextBtn.isEnabled = count&&phone
    }
}