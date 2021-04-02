package com.example.vaseisapp.ui.dashboard.calculator.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemGroupBinding
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import com.example.vaseisapp.ui.diffutil.GROUP_ITEM_DIFF_UTIL

class GroupAdapter(private val listener: GroupListener) : ListAdapter<GroupItem, GroupAdapter.GroupBindingViewHolder>(GROUP_ITEM_DIFF_UTIL) {

    private var selected: Int = 0

    interface GroupListener {
        fun onGroupClickListener(selectedGroup: GroupItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGroupBinding.inflate(layoutInflater, parent, false)
        return GroupBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupBindingViewHolder, position: Int) {
        holder.bindTo(getItem(position), position, getItem(selected), selected)
    }

    inner class GroupBindingViewHolder(private val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: GroupItem, position: Int, oldItem: GroupItem, oldPossition: Int) {
            with(binding) {
                groupTitleTextView.text = item.name

                if (item.isSelected) {
                    groupTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.black))
                    groupTitleTextView.background = ContextCompat.getDrawable(root.context, R.drawable.shape_group_not_selected)
                    groupTitleTextView.setTypeface(groupTitleTextView.typeface, Typeface.BOLD)
                } else {
                    groupTitleTextView.background = null
                    groupTitleTextView.setTextColor(ContextCompat.getColor(root.context, R.color.text_dr_grey))
                    groupTitleTextView.setTypeface(null, Typeface.NORMAL)
                }

                root.setOnClickListener {
                    if (oldItem != item) {
                        listener.onGroupClickListener(item, position)
                        item.isSelected = true
                        oldItem.isSelected = false
                        notifyDataSetChanged()
                        selected = position
                    }
                }
            }
        }
    }
}
