package com.example.vaseisapp.ui.dashboard.departmentcenter.department.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemDepartmentCountBinding
import com.example.vaseisapp.ui.diffutil.STRING_DIFF_UTIL

class DepartmentCountAdapter() : ListAdapter<String, DepartmentCountAdapter.DepartmentCountViewHolder>(STRING_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentCountViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDepartmentCountBinding.inflate(layoutInflater, parent, false)
        return DepartmentCountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DepartmentCountViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class DepartmentCountViewHolder(private val binding: ItemDepartmentCountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(total: String) {
            binding.totalTextView.text = String.format(binding.root.context.getString(R.string.bases_university_count), total)
        }
    }
}