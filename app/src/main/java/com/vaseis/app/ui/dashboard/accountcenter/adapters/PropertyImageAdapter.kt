package com.vaseis.app.ui.dashboard.accountcenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemAccountBottomImageBinding
import com.vaseis.app.ui.diffutil.PROPERTY_IMAGE_ITEM_DIFF_UTIL

class PropertyImageAdapter : ListAdapter<Int, PropertyImageAdapter.PropertyImageViewHolder>(PROPERTY_IMAGE_ITEM_DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAccountBottomImageBinding.inflate(layoutInflater, parent, false)
        return PropertyImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyImageViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class PropertyImageViewHolder(private val binding: ItemAccountBottomImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(imgId: Int) {
            binding.logoImageView.setImageResource(imgId)
        }
    }
}