package com.example.vaseisapp.ui.dashboard.departmentcenter.department.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemDepartmentBinding
import com.example.vaseisapp.ui.dashboard.departmentcenter.department.models.DepartmentWithSelected
import com.example.vaseisapp.ui.diffutil.DEPARTMENT_DIFF_UTIL

class DepartmentAdapter(
    private val listener: DepartmentClickListener
) : ListAdapter<DepartmentWithSelected, DepartmentAdapter.UniversityViewHolder>(DEPARTMENT_DIFF_UTIL) {
    private var currentSelection = 0

    interface DepartmentClickListener {
        fun onItemClickListener(deptCode: String, deptName: String)
        fun onCodeClickListener(position: Int)
        fun onItemLongClickListener(position: Int): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDepartmentBinding.inflate(layoutInflater, parent, false)
        return UniversityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    //todo fix problem with return wrong position after update adapter
    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class UniversityViewHolder(private val binding: ItemDepartmentBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bindTo(department: DepartmentWithSelected, position: Int) {
            with(binding) {
                try {
                    titleTextView.text = department.name.substring(0, department.name.indexOf("("))
                    cityAndUniversityTextView.text = String.format(
                        "${department.uniTitle}, ${
                            department.name.substring(
                                department.name.indexOf("(") + 1,
                                department.name.indexOf(")")
                            )
                        }"
                    )
                } catch (e: StringIndexOutOfBoundsException) {
                    titleTextView.text = department.name
                }

                Glide.with(root).load("https://ece.uowm.gr/images/Logorollover.png").diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().into(logoImageView)

                root.setOnClickListener {
                    listener.onItemClickListener(department.code, department.name)
                }

                root.setOnLongClickListener {
                    listener.onItemLongClickListener(position)
                }

                idTextView.setOnClickListener {
                    listener.onCodeClickListener(position)
                }

                if (department.isSelected) {
                    backgroundView.setBackgroundColor(ContextCompat.getColor(root.context, R.color.university_selected_background))
                    idTextView.text = ""
                    idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.ic_check)
                } else {
                    root.background = ContextCompat.getDrawable(root.context, R.drawable.cardview_background_16_clickable)
                    idTextView.text = department.code
                    idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.circle_view)
                }


                if (!department.isNowSelected)
                    return

                //text flip
                val oa1: ObjectAnimator = ObjectAnimator.ofFloat(idTextView, "scaleX", 1f, 0f)
                val oa2: ObjectAnimator = ObjectAnimator.ofFloat(idTextView, "scaleX", 0f, 1f)
                oa1.interpolator = DecelerateInterpolator()
                oa2.interpolator = AccelerateDecelerateInterpolator()
                oa1.duration = 200
                oa2.duration = 200

                oa1.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        if (department.isSelected) {
                            idTextView.text = ""
                            idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.ic_check)
                        } else {
                            idTextView.text = department.code.toString()
                            idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.circle_view)
                        }
                        oa2.start()
                        department.isNowSelected = false
                    }
                })
                oa1.start()
            }
        }
    }
}