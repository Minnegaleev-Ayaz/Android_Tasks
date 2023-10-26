package com.example.android_tasks.ui_fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.Adapters.OprosnikAdapter
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.R
import com.example.android_tasks.databinding.FragmentOprosnikBinding
import com.example.android_tasks.utils.ParamsKey
import com.example.android_tasks.utils.QuestionsGenerator

class OprosnikFragment : BaseFragment(R.layout.fragment_oprosnik), AnswerChangedListener {
    private val viewBinding: FragmentOprosnikBinding by viewBinding(FragmentOprosnikBinding::bind)
    private var vpAdapter: OprosnikAdapter? = null
    private var answers: HashMap<Int, Int> = hashMapOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    fun initView() {
        val cntques = arguments?.getInt(ParamsKey.QUESTION_COUNT_KEY)!!.toInt()
        val questions = QuestionsGenerator.generateQuestions(cntques)
        vpAdapter = OprosnikAdapter(
            questions,
            manager = childFragmentManager,
            lifecycle
        )
        with(viewBinding) {
            fragmentVp.apply {
                adapter = vpAdapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        val realPos = when (position) {
                            0 -> cntques
                            cntques + 1 -> 1
                            else -> position
                        }
                        pageTv.text = "Вопрос №${position + 1} из ${cntques + 1}"
                    }
                })

            }
            lastBtn.setOnClickListener {
                showToast("Вы прошли тест")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onAnswerChanged(questionPosition: Int, answerPosition: Int) {
        answers[questionPosition] = answerPosition
        val numberOfQuestions = requireArguments().getInt(ParamsKey.QUESTION_COUNT_KEY)
        if (answers.count() == numberOfQuestions+1) {
            viewBinding.lastBtn.visibility = View.VISIBLE
        }
    }

    companion object {
        const val OPROSNIK_FRAGMENT_TAG = "OPROSNIK_FRAGMENT_TAG"
        fun newInstance(count: Int) = OprosnikFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.QUESTION_COUNT_KEY, count)
            }
        }
    }
}