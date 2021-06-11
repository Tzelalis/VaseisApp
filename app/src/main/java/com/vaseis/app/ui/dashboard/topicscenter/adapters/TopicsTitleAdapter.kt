package com.vaseis.app.ui.dashboard.topicscenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaseis.app.databinding.ItemTopicTitleBinding
import com.vaseis.app.domain.topics.Topic
import com.vaseis.app.ui.diffutil.TOPIC_ITEM_DIFF_UTIL

class TopicsTitleAdapter : ListAdapter<Topic, TopicsTitleAdapter.TopicsViewHolder>(TOPIC_ITEM_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopicTitleBinding.inflate(layoutInflater, parent, false)
        return TopicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class TopicsViewHolder(private val binding: ItemTopicTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(topic: Topic) {
            with(binding) {
                topicTitleTextView.text = topic.pdfUrl
            }
        }
    }
}