package com.example.vaseisapp.ui.personalization.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.databinding.ItemPersonalizationCategoryBinding
import com.example.vaseisapp.ui.diffutil.CATEGORY_ITEM_DIFF_UTIL

class CategoryListAdapter(private val listener: CategoryClickListener) :
    ListAdapter<Category, CategoryListAdapter.CategoryViewHolder>(CATEGORY_ITEM_DIFF_UTIL) {

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
        fun bindTo(category: Category, position: Int) {
            with(binding) {
                categoryTitleTextView.text = category.title
                categoryDescriptionTextView.text = category.description

                root.setOnClickListener { listener.onClickListener(position) }
            }
        }
    }
}