package com.example.vaseisapp.ui.personalization.examtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.databinding.ItemPersonalizationCategoryBinding
import com.example.vaseisapp.ui.diffutil.CATEGORY_ITEM_DIFF_UTIL

class ExamTypeListAdapter(private val listener: CategoryClickListener) :
    ListAdapter<ExamType, ExamTypeListAdapter.CategoryViewHolder>(CATEGORY_ITEM_DIFF_UTIL) {

    interface CategoryClickListener {
        fun onClickListener(position: Int)
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
        fun bindTo(examType: ExamType, position: Int) {
            with(binding) {
                examTypeTitleTextView.text = examType.title
                examTypeDescriptionTextView.text = examType.description

                root.setOnClickListener { listener.onClickListener(position) }
            }
        }
    }
}