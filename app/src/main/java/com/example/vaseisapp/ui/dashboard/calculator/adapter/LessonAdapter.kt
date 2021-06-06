package com.example.vaseisapp.ui.dashboard.calculator.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.databinding.ItemLessonsBinding
import com.example.vaseisapp.ui.dashboard.calculator.model.LessonItem
import com.example.vaseisapp.ui.diffutil.LESSON_ITEM_DIFF_UTIL
import com.example.vaseisapp.utils.filters.DecimalDigitsInputFilter
import com.example.vaseisapp.utils.filters.MinMaxFilter


class LessonAdapter(private val listener: LessonListener) : ListAdapter<LessonItem, LessonAdapter.LessonViewHolder>(LESSON_ITEM_DIFF_UTIL) {

    companion object    {
        private const val NORMAL_ITEM = 0
        private const val LAST_ITEM = 1
    }

    interface LessonListener {
        fun onLessonTextChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonsBinding.inflate(layoutInflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        if(getItemViewType(position) == LAST_ITEM)
            holder.bindLastTo()
        else
            holder.bindTo(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size - 1) LAST_ITEM else NORMAL_ITEM
    }

    inner class LessonViewHolder(private val binding: ItemLessonsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: LessonItem) {
            with(binding) {
                lessonsTextView.text = item.calculatorLesson.shortName
                lessonsNumberPicker.binding.lessonEdittext.setText(item.degree)
                lessonsNumberPicker.binding.lessonEdittext.filters = arrayOf(MinMaxFilter(0.0, 20.0), DecimalDigitsInputFilter(2, 2))

                lessonsNumberPicker.binding.lessonEdittext.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {}
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(
                        s: CharSequence, start: Int, before: Int, count: Int
                    ) {
                        item.degree = s.toString()
                        listener.onLessonTextChanged()
                    }
                })
            }
        }

        fun bindLastTo()    {

        }
    }
}

