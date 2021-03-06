package com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemYearBinding
import com.vaseis.app.ui.diffutil.STRING_DIFF_UTIL

class YearsAdapter(private val listener: YearsListener) : ListAdapter<String, YearsAdapter.YearsViewHolder>(STRING_DIFF_UTIL) {

    interface YearsListener {
        fun onYearClick(year : String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemYearBinding.inflate(layoutInflater, parent, false)
        return YearsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YearsViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    inner class YearsViewHolder(private val binding: ItemYearBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(year: String, itemPosition: Int) {
            with(binding) {
                yearTextView.text = year
                root.setOnClickListener { listener.onYearClick(year, itemPosition) }
            }
        }
    }
}