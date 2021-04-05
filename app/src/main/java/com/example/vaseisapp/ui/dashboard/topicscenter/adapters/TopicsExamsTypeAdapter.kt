package com.example.vaseisapp.ui.dashboard.topicscenter.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemGroupBinding

import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import com.example.vaseisapp.ui.diffutil.EXAM_TYPE_ITEM_DIFF_UTIL


class TopicsExamsTypeAdapter(private val listener: ExamTypeItemListener) :
    ListAdapter<ExamTypeItem, TopicsExamsTypeAdapter.ExamTypeItemBindingViewHolder>(
        EXAM_TYPE_ITEM_DIFF_UTIL
    ) {

    private var selected: Int = 0

    interface ExamTypeItemListener {
        fun onExamTypeItemClickListener(selectedGroup: ExamTypeItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamTypeItemBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGroupBinding.inflate(layoutInflater, parent, false)
        return ExamTypeItemBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExamTypeItemBindingViewHolder, position: Int) {
        holder.bindTo(getItem(position), position, getItem(selected), selected)
    }

    inner class ExamTypeItemBindingViewHolder(private val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: ExamTypeItem, position: Int, oldItem: ExamTypeItem, oldPossition: Int) {
            with(binding) {
                groupTitleTextView.text = item.examType.short_name

                if (item.isSelected) {
                    groupTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.black))
                    groupTitleTextView.background = ContextCompat.getDrawable(root.context, R.drawable.shape_group_not_selected)
                    groupTitleTextView.setTypeface(groupTitleTextView.typeface, Typeface.BOLD)
                } else {
                    groupTitleTextView.background = null
                    groupTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.text_dr_grey))
                    groupTitleTextView.setTypeface(null, Typeface.NORMAL)
                }

                root.setOnClickListener {
                    if (oldItem != item) {
                        listener.onExamTypeItemClickListener(item, position)
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
