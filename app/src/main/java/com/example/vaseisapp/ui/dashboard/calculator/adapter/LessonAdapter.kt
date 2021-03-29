package com.example.vaseisapp.ui.dashboard.calculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.databinding.ItemLessonsBinding
import com.example.vaseisapp.domain.calculation.entities.Lesson
import com.example.vaseisapp.ui.diffutil.LESSON_ITEM_DIFF_UTIL


class LessonAdapter : ListAdapter<Lesson, LessonAdapter.LessonViewHolder>(LESSON_ITEM_DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonsBinding.inflate(layoutInflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class LessonViewHolder(private val binding: ItemLessonsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(lesson: Lesson) {
            with(binding) {
                lessonsTextView.text = lesson.shortName


            }
        }
    }
}

