package com.example.vaseisapp.ui.dashboard.departmentcenter.filters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemBaseFilterUniversityBinding
import com.example.vaseisapp.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.example.vaseisapp.ui.diffutil.UNIVERSITY_ITEM_DIFF_UTIL

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
        val binding = ItemBaseFilterUniversityBinding.inflate(layoutInflater, parent, false)
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

    inner class FilterUniversityViewHolder(private val binding: ItemBaseFilterUniversityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: UniversityFilterItem) {
            with(binding) {
                universityNameTextview.text = item.university.title
                universityCheckbox.isChecked = item.isSelected

                root.setOnClickListener {
                    universityCheckbox.isChecked = !universityCheckbox.isChecked
                    item.isSelected = universityCheckbox.isChecked
                }

                universityCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (buttonView.isPressed)
                        item.isSelected = isChecked
                }
            }
        }

        fun bindFirstTo(item: UniversityFilterItem) {
            with(binding) {
                universityNameTextview.text = root.resources.getString(R.string.bases_university_select_all)
                universityCheckbox.isChecked = item.isSelected
                universityNameTextview.typeface = ResourcesCompat.getFont(root.context, R.font.roboto_bold);

                root.setOnClickListener {
                    listener.selectAllClickListener()
                }

                universityCheckbox.setOnClickListener {
                    listener.selectAllClickListener()
                }
            }
        }
    }
}