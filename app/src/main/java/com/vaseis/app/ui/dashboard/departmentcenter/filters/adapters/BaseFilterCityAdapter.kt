package com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.R
import com.vaseis.app.databinding.ItemBaseFilterUniversityBinding
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.CityFilterItem
import com.vaseis.app.ui.diffutil.CITY_ITEM_DIFF_UTIL

class BaseFilterCityAdapter(private val listener: FilterCityListener) :
    ListAdapter<CityFilterItem, BaseFilterCityAdapter.FilterCityViewHolder>(CITY_ITEM_DIFF_UTIL) {

    companion object {
        private const val FIRST_ITEM = 1
        private const val NORMAL_ITEM = 0
    }

    interface FilterCityListener {
        fun selectAllClickListener()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBaseFilterUniversityBinding.inflate(layoutInflater, parent, false)
        return FilterCityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterCityViewHolder, position: Int) {
        if (getItemViewType(position) == FIRST_ITEM)
            holder.bindFirstTo(getItem(position))
        else
            holder.bindTo(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) FIRST_ITEM else NORMAL_ITEM
    }

    inner class FilterCityViewHolder(private val binding: ItemBaseFilterUniversityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: CityFilterItem) {
            with(binding) {
                universityNameTextview.text = item.city
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

        fun bindFirstTo(item: CityFilterItem) {
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