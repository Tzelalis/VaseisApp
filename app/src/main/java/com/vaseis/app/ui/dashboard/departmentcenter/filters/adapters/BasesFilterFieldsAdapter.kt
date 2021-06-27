package com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemRvCheckboxBinding
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.FieldFilterItem
import com.vaseis.app.ui.diffutil.FIELD_FILTER_ITEM_DIFF_UTIL

class BasesFilterFieldsAdapter : ListAdapter<FieldFilterItem, BasesFilterFieldsAdapter.BasesFilterFieldsViewHolder>(FIELD_FILTER_ITEM_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasesFilterFieldsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvCheckboxBinding.inflate(layoutInflater, parent, false)
        return BasesFilterFieldsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasesFilterFieldsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class BasesFilterFieldsViewHolder(val binding: ItemRvCheckboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item : FieldFilterItem) {
            with(binding){
                textView.text = item.field.fullName
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
    }
}