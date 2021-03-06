package com.vaseis.app.ui.dashboard.departmentcenter.department.adapters

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
import com.vaseis.app.R
import com.vaseis.app.databinding.ItemDepartmentBinding
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.DepartmentWithSelected
import com.vaseis.app.ui.diffutil.DEPARTMENT_DIFF_UTIL

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
        fun bindTo(departmentItem: DepartmentWithSelected, position: Int) {
            with(binding) {
                try {
                    titleTextView.text = departmentItem.department.name.substring(0, departmentItem.department.name.indexOf("("))
                    cityAndUniversityTextView.text = String.format(
                        "${departmentItem.department.uniTitle}, ${
                            departmentItem.department.name.substring(
                                departmentItem.department.name.indexOf("(") + 1,
                                departmentItem.department.name.indexOf(")")
                            )
                        }"
                    )
                } catch (e: StringIndexOutOfBoundsException) {
                    titleTextView.text = departmentItem.department.name
                }

                setupLogoImg(departmentItem.department.logoUrl, departmentItem.department.uniLogoURL)

                root.setOnClickListener {
                    listener.onItemClickListener(departmentItem.department.code, departmentItem.department.name)
                }

                /*root.setOnLongClickListener {
                    listener.onItemLongClickListener(position)
                }*/

                idTextView.setOnClickListener {
                    listener.onCodeClickListener(position)
                }

                if (departmentItem.isSelected) {
                    backgroundView.setBackgroundColor(ContextCompat.getColor(root.context, R.color.university_selected_background))
                    idTextView.text = ""
                    idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.ic_check)
                } else {
                    root.background = ContextCompat.getDrawable(root.context, R.drawable.cardview_background_16_clickable)
                    idTextView.text = departmentItem.department.code
                    idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.circle_view)
                }


                if (!departmentItem.isNowSelected)
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
                        if (departmentItem.isSelected) {
                            idTextView.text = ""
                            idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.ic_check)
                        } else {
                            idTextView.text = departmentItem.department.code
                            idTextView.background = ContextCompat.getDrawable(root.context, R.drawable.circle_view)
                        }
                        oa2.start()
                        departmentItem.isNowSelected = false
                    }
                })
                oa1.start()
            }
        }

        private fun setupLogoImg(deptLogo : String?, uniLogo : String?)  {
            if(!deptLogo.isNullOrBlank()){
                Glide.with(binding.root.context).load(deptLogo).diskCacheStrategy(DiskCacheStrategy.ALL).centerInside().into(binding.logoImageView)
                return
            }

            if(!uniLogo.isNullOrBlank())    {

                Glide.with(binding.root.context).load(uniLogo).diskCacheStrategy(DiskCacheStrategy.ALL).centerInside().into(binding.logoImageView)

                return
            }

        }
    }
}