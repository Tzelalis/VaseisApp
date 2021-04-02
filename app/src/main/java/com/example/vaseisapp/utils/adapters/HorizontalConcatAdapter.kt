package com.example.vaseisapp.utils.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vaseisapp.R
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsAdapter
import kotlinx.android.synthetic.main.item_topic_concat_row.view.*


class HorizontalConcatAdapter(private val context: Context, private val topicAdapter: TopicsAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

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
            is ConcatViewHolder -> holder.bind(topicAdapter)
            else -> Log.e(this.toString(), "No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")

        }
    }

    inner class ConcatViewHolder(itemView: View) : BaseConcatHolder<TopicsAdapter>(itemView) {
        override fun bind(adapter: TopicsAdapter) {
            itemView.topics_lesson_recycler_view.adapter = adapter
            itemView.topics_lesson_recycler_view.setHasFixedSize(true)

        }
    }
}