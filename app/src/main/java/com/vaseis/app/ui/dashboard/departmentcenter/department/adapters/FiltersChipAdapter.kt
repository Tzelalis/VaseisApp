package com.vaseis.app.ui.dashboard.departmentcenter.department.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemFilterChipBinding
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.FilterChip
import com.vaseis.app.ui.diffutil.FILTER_CHIP_ITEM_DIFF_UTIL

class FiltersChipAdapter(private val listener: FiltersChipListener) :
    ListAdapter<FilterChip, FiltersChipAdapter.FiltersChipViewHolder>(FILTER_CHIP_ITEM_DIFF_UTIL) {

    interface FiltersChipListener {
        fun onFilterChipClickListener(filter: FilterChip, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersChipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterChipBinding.inflate(layoutInflater, parent, false)
        return FiltersChipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FiltersChipViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    inner class FiltersChipViewHolder(private val binding: ItemFilterChipBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(filter: FilterChip, position: Int) {
            with(binding) {
                nameTxt.text = filter.name

                root.setOnClickListener { listener.onFilterChipClickListener(filter, position) }
            }
        }
    }
}
