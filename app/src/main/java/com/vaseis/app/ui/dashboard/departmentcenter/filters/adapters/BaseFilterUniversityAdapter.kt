package com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.R
import com.vaseis.app.databinding.ItemRvCheckboxBinding
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.vaseis.app.ui.diffutil.UNIVERSITY_ITEM_DIFF_UTIL

class BaseFilterUniversityAdapter(private val listener: FilterUniversityListener) :
    ListAdapter<UniversityFilterItem, BaseFilterUniversityAdapter.FilterUniversityViewHolder>(UNIVERSITY_ITEM_DIFF_UTIL) {

    companion object {
        private const val FIRST_ITEM = 1
        private const val NORMAL_ITEM = 0
    }

    interface FilterUniversityListener {
        fun selectAllClickListener()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterUniversityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvCheckboxBinding.inflate(layoutInflater, parent, false)
        return FilterUniversityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterUniversityViewHolder, position: Int) {
        if (getItemViewType(position) == FIRST_ITEM)
            holder.bindFirstTo(getItem(position))
        else
            holder.bindTo(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) FIRST_ITEM else NORMAL_ITEM
    }

    inner class FilterUniversityViewHolder(private val binding: ItemRvCheckboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: UniversityFilterItem) {
            with(binding) {
                textView.text = item.university.title
                checkbox.isChecked = item.isSelected

                root.setOnClickListener {
                    checkbox.isChecked = !checkbox.isChecked
                    item.isSelected = checkbox.isChecked
                }

                checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (buttonView.isPressed)
                        item.isSelected = isChecked
                }
            }
        }

        fun bindFirstTo(item: UniversityFilterItem) {
            with(binding) {
                textView.text = root.resources.getString(R.string.bases_university_select_all)
                checkbox.isChecked = item.isSelected
                textView.typeface = ResourcesCompat.getFont(root.context, R.font.roboto_bold);

                root.setOnClickListener {
                    listener.selectAllClickListener()
                }

                checkbox.setOnClickListener {
                    listener.selectAllClickListener()
                }
            }
        }
    }
}