package com.example.vaseisapp.ui.personalization.group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemPersonalizationCategoryBinding
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import com.example.vaseisapp.ui.diffutil.GROUP_ITEM_DIFF_UTIL

class GroupTypeListAdapter : ListAdapter<GroupItem, GroupTypeListAdapter.GroupTypeViewHolder>(GROUP_ITEM_DIFF_UTIL) {

    private var selectedIndex: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonalizationCategoryBinding.inflate(layoutInflater, parent, false)
        return GroupTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupTypeViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    inner class GroupTypeViewHolder(private val binding: ItemPersonalizationCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(groupItem: GroupItem, position: Int) {
            with(binding) {
                examTypeTitleTextView.text = groupItem.group.shortName


                if (groupItem.isSelected) {
                    root.setBackgroundResource(R.drawable.cardview_background_16_selectable)
                    examTypeTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.white))
                    selectedIndex = position
                } else {
                    root.setBackgroundResource(R.drawable.cardview_background_16_white)
                    examTypeTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.text_dr_grey))
                }


                root.setOnClickListener {
                    selectedIndex?.let { index ->
                        getItem(index).isSelected = false
                        groupItem.isSelected = true
                    }
                    selectedIndex = position
                    notifyDataSetChanged()
                }
            }
        }
    }
}