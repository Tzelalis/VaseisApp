package com.vaseis.app.ui.dashboard.accountcenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.vaseis.app.databinding.ItemPropertyBinding
import com.vaseis.app.databinding.ItemPropertyTitleBinding
import com.vaseis.app.ui.dashboard.accountcenter.model.PropertyItem
import com.vaseis.app.ui.diffutil.PROPERTY_ITEM_DIFF_UTIL

class PropertyAdapter(private val listener : PropertyListener) : ListAdapter<PropertyItem, PropertyAdapter.PropertyViewHolder>(PROPERTY_ITEM_DIFF_UTIL) {

    companion object {
        const val TITLE_BINDING = 1
        const val NORMAL_BINDING = 2
    }

    interface PropertyListener  {
        fun onClickListener(property : PropertyItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        if (viewType == TITLE_BINDING) {
            val binding = ItemPropertyTitleBinding.inflate(layoutInflater, parent, false)
            return PropertyViewHolder(binding)
        }

        val binding = ItemPropertyBinding.inflate(layoutInflater, parent, false)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        if (position == 0) {
            holder.titleBindTo(getItem(position))
        } else {
            holder.bindTo(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TITLE_BINDING

        return NORMAL_BINDING
    }

    inner class PropertyViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(property: PropertyItem) {
            val binding = this.binding as? ItemPropertyBinding
            binding?.let {
                with(binding) {
                    titleTextView.text = property.title
                    fieldTextView.text = property.field

                    root.setOnClickListener { listener.onClickListener(property) }
                }
            }
        }

        fun titleBindTo(property: PropertyItem) {
            val binding = this.binding as? ItemPropertyTitleBinding
            binding?.let {
                with(binding) {
                    titleTextView.text = property.title
                    titleTextView.setPadding(0, 4, 0, 4)
                }
            }
        }
    }
}