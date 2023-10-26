package com.example.android_tasks.ui_fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_tasks.Adapters.AnswerAdapter
import com.example.android_tasks.Adapters.OprosnikAdapter
import com.example.android_tasks.Base.BaseActivity
import com.example.android_tasks.Base.BaseFragment
import com.example.android_tasks.MainActivity
import com.example.android_tasks.Models.Answer
import com.example.android_tasks.Models.Question
import com.example.android_tasks.R
import com.example.android_tasks.databinding.FragmentAnswerBinding
import com.example.android_tasks.utils.ParamsKey
import kotlinx.coroutines.internal.artificialFrame

class AnswerFragment : BaseFragment(R.layout.fragment_answer) {
    private val viewBinding: FragmentAnswerBinding by viewBinding(FragmentAnswerBinding::bind)
    private var rvAdapter: AnswerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    fun initViews() {
        val question = arguments?.getSerializable(ParamsKey.QUESTION_KEY) as Question
        with(viewBinding) {
            answerRv.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvAdapter = AnswerAdapter(question.answers.toMutableList(),
                onItemChecked = { pos -> updateAns(pos) },
                onRootClicked = { pos -> updateAns(pos) })
            titleTv.text = question.text

            answerRv.adapter = rvAdapter
        }

    }
    private fun updateAns(pos: Int) {
        rvAdapter?.apply {
            val pointed_pos = items.indexOfFirst { it.isChecked == true }
            if (pointed_pos != -1) {
                items[pointed_pos].isChecked = false
                notifyItemChanged(pointed_pos)
            }
            items[pos].isChecked = true
            notifyItemChanged(pos)

            val listener: AnswerChangedListener =
                (requireActivity() as BaseActivity)
                    .supportFragmentManager
                    .findFragmentByTag(OprosnikFragment.OPROSNIK_FRAGMENT_TAG)
                        as AnswerChangedListener

            listener.onAnswerChanged(
                requireArguments().getInt(ParamsKey.POSITION_KEY),
                pos
            )
        }
    }
    companion object {
        const val ANSWER_FRAGMENT_TAG = "ANSWER_FRAGMENT_TAG"
        fun newInstance(questionPosition: Int, question: Question) = AnswerFragment().apply {
            arguments = Bundle().apply {
                putInt(ParamsKey.POSITION_KEY, questionPosition)
                putSerializable(ParamsKey.QUESTION_KEY, question)
            }
        }
    }
}