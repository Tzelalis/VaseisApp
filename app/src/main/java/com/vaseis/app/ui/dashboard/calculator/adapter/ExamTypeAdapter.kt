package com.vaseis.app.ui.dashboard.calculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemCalculatorExamTypeBinding
import com.vaseis.app.ui.dashboard.calculator.model.CalculatorExamTypeItem
import com.vaseis.app.ui.diffutil.EXAM_TYPE_ITEM_DIFF_UTIL

/*
class ExamTypeAdapter(private val listener: CalculatorExamTypeListener) :
    ListAdapter<CalculatorExamTypeItem, ExamTypeAdapter.CalculatorExamTypeViewHolder>(EXAM_TYPE_ITEM_DIFF_UTIL) {

    interface CalculatorExamTypeListener {
        fun onCalculatorExamTypeClick(item: CalculatorExamTypeItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorExamTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCalculatorExamTypeBinding.inflate(layoutInflater, parent, false)
        return CalculatorExamTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalculatorExamTypeViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class CalculatorExamTypeViewHolder(private val binding: ItemCalculatorExamTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(itemCalculator: CalculatorExamTypeItem) {
            with(binding) {
                binding.examTypeTitleTextView.text = itemCalculator.shortName

                root.setOnClickListener { listener.onCalculatorExamTypeClick(itemCalculator) }
            }
        }
    }
}*/
