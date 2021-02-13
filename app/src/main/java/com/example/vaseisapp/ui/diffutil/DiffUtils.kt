package com.example.vaseisapp.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.vaseisapp.ui.department.DepartmentWithSelected
import com.example.vaseisapp.ui.departmentdetails.DepartmentItem

val DEPARTMENT_DIFF_UTIL = object : DiffUtil.ItemCallback<DepartmentWithSelected>() {
    override fun areItemsTheSame(oldItem: DepartmentWithSelected, newItem: DepartmentWithSelected): Boolean =
        oldItem.code == newItem.code

    override fun areContentsTheSame(oldItem: DepartmentWithSelected, newItem: DepartmentWithSelected): Boolean =
        oldItem == newItem
}

val DEPARTMENT_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<DepartmentItem>() {
    override fun areItemsTheSame(oldItem: DepartmentItem, newItem: DepartmentItem): Boolean = oldItem.deptCode == newItem.deptCode

    override fun areContentsTheSame(oldItem: DepartmentItem, newItem: DepartmentItem): Boolean = oldItem == newItem
}

val YEAR_ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}

