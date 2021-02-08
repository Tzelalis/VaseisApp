package com.example.vaseisapp.ui.departmentdetails

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.alpha
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemDepartmentsComparisonBinding
import com.example.vaseisapp.ui.diffutil.DEPARTMENT_ITEM_DIFF_UTIL

class DepartmentsComparisonAdapter(private val listener : DepartmentsListener) : ListAdapter<DepartmentItem, DepartmentsComparisonAdapter.DepartmentsComparisonViewHolder>(DEPARTMENT_ITEM_DIFF_UTIL){

    interface DepartmentsListener{
        fun onDepartmentClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentsComparisonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDepartmentsComparisonBinding.inflate(layoutInflater, parent, false)
        return DepartmentsComparisonViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: DepartmentsComparisonViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    inner class DepartmentsComparisonViewHolder(private val binding : ItemDepartmentsComparisonBinding, private val context : Context) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item : DepartmentItem, position: Int){
            with(binding){
                codeTextView.text = item.deptCode
                nameTextView.text = item.deptName
                codeLinearLayout.background = ResourcesCompat.getDrawable(context.resources, item.color, null)
                //codeLinearLayout.background = context.resources.getDrawable(item.color, null)


                if(item.isBackgroundColorful){
                    val color = ColorDrawable(ResourcesCompat.getColor(context.resources, item.color, null))
                    color.alpha = 210
                    mainConstraintLayout.background = color


                    nameTextView.setTextColor(context.resources.getColor(R.color.white))
                }else{
                    mainConstraintLayout.background = ResourcesCompat.getDrawable(context.resources, R.color.design_default_color_background, null)
                    nameTextView.setTextColor(context.resources.getColor(android.R.color.darker_gray))
                }



                root.setOnClickListener {
                    listener.onDepartmentClick(position)
                }
            }
        }
    }
}