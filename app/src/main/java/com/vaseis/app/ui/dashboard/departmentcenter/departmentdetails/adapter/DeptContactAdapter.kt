package com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.R
import com.vaseis.app.databinding.ItemContactDeptBinding
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DeptConcatType
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DeptContactItem
import com.vaseis.app.ui.diffutil.DEPT_CONTACT_ITEM_DIFF_UTIL

class DeptContactAdapter(private val adapterDept: DeptContactListener) :
    ListAdapter<DeptContactItem, DeptContactAdapter.ContactViewHolder>(DEPT_CONTACT_ITEM_DIFF_UTIL) {

    interface DeptContactListener {
        fun onContactOnClick(item: DeptContactItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactDeptBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class ContactViewHolder(private val binding: ItemContactDeptBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: DeptContactItem) {
            with(binding) {
                when(item.type){
                    DeptConcatType.PHONE -> contactImg.setImageResource(R.drawable.ic_baseline_phone_24)
                    DeptConcatType.WEBSITE -> contactImg.setImageResource(R.drawable.ic_baseline_website_24)
                    DeptConcatType.EMAIL -> contactImg.setImageResource(R.drawable.ic_baseline_email_24)
                }

                root.setOnClickListener { adapterDept.onContactOnClick(item) }
            }
        }
    }
}