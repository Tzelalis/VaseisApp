package com.vaseis.app.ui.dashboard.calculator.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.R
import com.vaseis.app.utils.adapters.BaseConcatHolder
import kotlinx.android.synthetic.main.item_lessons_concat_row.view.*
import kotlin.math.abs
import kotlin.math.sign


class HorizontalLessonsAdapter(private val context: Context, private val lessonAdapter: LessonAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    fun getAdapter(): LessonAdapter {
        return lessonAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lessons_concat_row, parent, false)
        return ConcatViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(lessonAdapter, "")
            else -> Log.e(this.toString(), "No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(itemView: View) : BaseConcatHolder<LessonAdapter>(itemView) {
        private val MAX_VELOCITY_Y = 8000

        override fun bind(adapter: LessonAdapter, key: String) {
            itemView.calculator_lesson_recycler_view.apply {
                this.adapter = adapter
                setHasFixedSize(true)

                onFlingListener = object : RecyclerView.OnFlingListener() {
                    override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                        if (abs(velocityY) > this@ConcatViewHolder.MAX_VELOCITY_Y) {
                            val newVelocityY = this@ConcatViewHolder.MAX_VELOCITY_Y * sign(velocityY.toDouble()).toInt()
                            fling(velocityX, newVelocityY)
                            return true
                        }
                        return false
                    }
                }
            }
        }
    }
}