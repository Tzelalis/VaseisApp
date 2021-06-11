package com.vaseis.app.ui.dashboard.accountcenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemVersionBinding
import com.vaseis.app.ui.diffutil.STRING_DIFF_UTIL

class PropertyVersionAdapter : ListAdapter<String, PropertyVersionAdapter.PropertyVersionViewHolder>(STRING_DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyVersionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVersionBinding.inflate(layoutInflater, parent, false)
        return PropertyVersionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyVersionViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class PropertyVersionViewHolder(private val binding: ItemVersionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(version: String) {
            binding.versionTextView.text = version
        }
    }
}