package com.example.vaseisapp.ui.dashboard.calculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemCalculatorExamTypeBinding
import com.example.vaseisapp.ui.dashboard.calculator.model.ExamTypeItem
import com.example.vaseisapp.ui.diffutil.EXAM_TYPE_ITEM_DIFF_UTIL

class ExamTypeAdapter(private val listener : ExamTypeListener) : ListAdapter<ExamTypeItem, ExamTypeAdapter.ExamTypeViewHolder>(EXAM_TYPE_ITEM_DIFF_UTIL) {

    private var selected: Int = 0

    interface ExamTypeListener {
        fun onExamTypeListener(item: ExamTypeItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCalculatorExamTypeBinding.inflate(layoutInflater, parent, false)
        return ExamTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExamTypeViewHolder, position: Int) {
        holder.bindTo(getItem(position), position, getItem(selected), selected)
    }

    inner class ExamTypeViewHolder(private val binding: ItemCalculatorExamTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: ExamTypeItem, position: Int, oldItem: ExamTypeItem, oldPossition: Int) {
            with(binding) {
                examTypeTitleTextView.text = item.examType.short_name

                if (item.isSelected) {
                    examTypeTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.black))
                } else {
                    examTypeTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.text_dr_grey))
                }

                root.setOnClickListener {
                    if (oldItem != item) {
                        listener.onExamTypeListener(item, position)
                        item.isSelected = true
                        oldItem.isSelected = false
                        notifyDataSetChanged()
                        selected = position
                    }
                }
            }
        }
    }
}