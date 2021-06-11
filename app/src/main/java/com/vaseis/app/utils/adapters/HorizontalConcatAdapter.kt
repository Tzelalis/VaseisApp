package com.vaseis.app.utils.adapters

import android.content.Context
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.R
import com.vaseis.app.ui.dashboard.topicscenter.adapters.TopicsAdapter
import kotlinx.android.synthetic.main.item_topic_concat_row.view.*
import kotlin.math.abs
import kotlin.math.sign


class HorizontalConcatAdapter(private val context: Context, private val topicAdapter: TopicsAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    companion object {
        private const val RV_KEY = "recyc"
    }

    private val viewPool = RecyclerView.RecycledViewPool()  //create an instance of ViewPool
    private val scrollStates: MutableMap<String, Parcelable?> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val view = LayoutInflater.from(context).inflate(R.layout.item_topic_concat_row, parent, false)
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        view.topics_lesson_recycler_view.layoutManager = manager
        return ConcatViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(topicAdapter, RV_KEY)
            else -> Log.e(this.toString(), "No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    override fun onViewRecycled(holder: BaseConcatHolder<*>) {
        super.onViewRecycled(holder)

        //save horizontal scroll state
        scrollStates[RV_KEY] = holder.itemView.findViewById<RecyclerView>(R.id.topics_lesson_recycler_view).layoutManager?.onSaveInstanceState()
    }

    inner class ConcatViewHolder(itemView: View) : BaseConcatHolder<TopicsAdapter>(itemView) {
        private val MAX_VELOCITY_Y = 8000

        override fun bind(adapter: TopicsAdapter, key: String) {
            //restore horizontal scroll state
            val state = scrollStates[key]
            if (state != null) {
                itemView.topics_lesson_recycler_view.layoutManager?.onRestoreInstanceState(state)
            } else {
                itemView.topics_lesson_recycler_view.layoutManager?.scrollToPosition(0)
            }

            itemView.topics_lesson_recycler_view.apply {
                setRecycledViewPool(viewPool)
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