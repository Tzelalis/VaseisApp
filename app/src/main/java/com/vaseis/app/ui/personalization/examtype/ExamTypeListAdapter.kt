package com.vaseis.app.ui.personalization.examtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemRvRadioButtonBinding
import com.vaseis.app.ui.dashboard.topicscenter.model.ExamTypeItem
import com.vaseis.app.ui.diffutil.EXAM_TYPE_ITEM_DIFF_UTIL

class ExamTypeListAdapter(private val listener: ExamTypeClickListener) :
    ListAdapter<ExamTypeItem, ExamTypeListAdapter.CategoryViewHolder>(EXAM_TYPE_ITEM_DIFF_UTIL) {

    interface ExamTypeClickListener {
        fun onClickListener(item: ExamTypeItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvRadioButtonBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    inner class CategoryViewHolder(private val binding: ItemRvRadioButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(examTypeItem: ExamTypeItem, position: Int) {
            with(binding) {
                textView.text = examTypeItem.examType.shortName
                radioButton.isChecked = examTypeItem.isSelected

                root.setOnClickListener { changeCheckedType(currentList.indexOfFirst { it.isSelected }, examTypeItem) }
                radioButton.setOnClickListener { changeCheckedType(currentList.indexOfFirst { it.isSelected }, examTypeItem) }
            }
        }

        private fun changeCheckedType(oldPosition: Int, item: ExamTypeItem) {
            if (oldPosition > -1) {
                currentList[oldPosition]?.isSelected = false
                notifyItemChanged(oldPosition)
            }

            item.isSelected = true
            binding.radioButton.isChecked = true
        }
    }
}