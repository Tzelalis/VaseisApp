package com.example.vaseisapp.ui.personalization.examtype

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemPersonalizationCategoryBinding
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import com.example.vaseisapp.ui.diffutil.EXAM_TYPE_ITEM_DIFF_UTIL

class ExamTypeListAdapter(private val listener: ExamTypeClickListener) : ListAdapter<ExamTypeItem, ExamTypeListAdapter.CategoryViewHolder>(
    EXAM_TYPE_ITEM_DIFF_UTIL
) {

    private var selectedIndex: Int? = null

    interface ExamTypeClickListener {
        fun onClickListener(item: ExamTypeItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonalizationCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    inner class CategoryViewHolder(private val binding: ItemPersonalizationCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(examTypeItem: ExamTypeItem, position: Int) {
            with(binding) {
                examTypeTitleTextView.text = examTypeItem.examType.short_name


                if (examTypeItem.isSelected) {
                    root.setBackgroundResource(R.drawable.cardview_background_16_selectable)
                    examTypeTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.white))
                    selectedIndex = position
                } else {
                    root.setBackgroundResource(R.drawable.cardview_background_16_white)
                    examTypeTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.text_dr_grey))
                }

                root.setOnClickListener {
                    selectedIndex?.let { selectedIndex ->
                        getItem(selectedIndex).isSelected = false
                    }
                    examTypeItem.isSelected = true
                    notifyDataSetChanged()

                    listener.onClickListener(examTypeItem)
                }
            }
        }
    }
}