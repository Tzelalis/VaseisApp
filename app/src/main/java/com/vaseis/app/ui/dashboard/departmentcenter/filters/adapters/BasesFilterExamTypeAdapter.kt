package com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemRvRadioButtonBinding
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.BaseFilterExamTypeItem
import com.vaseis.app.ui.diffutil.BASE_FILTER_EXAM_TYPE_ITEM_DIFF_UTIL

class BasesFilterExamTypeAdapter :
    ListAdapter<BaseFilterExamTypeItem, BasesFilterExamTypeAdapter.BaseFilterExamTypeViewHolder>(BASE_FILTER_EXAM_TYPE_ITEM_DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseFilterExamTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvRadioButtonBinding.inflate(layoutInflater, parent, false)
        return BaseFilterExamTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseFilterExamTypeViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class BaseFilterExamTypeViewHolder(private val binding: ItemRvRadioButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: BaseFilterExamTypeItem) {
            with(binding) {
                textView.text = item.examType.fullName
                radioButton.isChecked = item.isSelected

                root.setOnClickListener { changeCheckedType(currentList.indexOfFirst { it.isSelected }, item) }
                radioButton.setOnClickListener { changeCheckedType(currentList.indexOfFirst { it.isSelected }, item) }
            }
        }

        private fun changeCheckedType(oldPosition: Int, item: BaseFilterExamTypeItem) {
            if (oldPosition > -1) {
                currentList[oldPosition]?.isSelected = false
                notifyItemChanged(oldPosition)
            }

            item.isSelected = true
            binding.radioButton.isChecked = true
        }
    }
}