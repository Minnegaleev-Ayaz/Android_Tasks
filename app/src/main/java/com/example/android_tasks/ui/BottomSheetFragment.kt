package com.example.android_tasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.android_tasks.Adapter.NewsAdapter
import com.example.android_tasks.Data.NewsRepository
import com.example.android_tasks.R
import com.example.android_tasks.Utils.ItemGenerator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(var adapter: NewsAdapter) :BottomSheetDialogFragment(){
    private var btnAddNews: Button? = null
    private var etAddNewsCount: EditText? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddNews = view.findViewById(R.id.btn_addNews)
        etAddNewsCount = view.findViewById(R.id.et_addNewsCount)

        btnAddNews?.setOnClickListener {
            val number = etAddNewsCount?.text.toString().toIntOrNull() ?: 0
            if (number in 1..5) {
                ItemGenerator.bottomSheetGeneration(number)
                adapter.setItems(ItemGenerator.currentList)
            }
            dismiss()
        }
    }
    companion object {
        private val BOTTOM_SHEET_DIALOG_FRAGMENT_TAG = "BOTTOM_SHEET_DIALOG_FRAGMENT_TAG"
    }
}