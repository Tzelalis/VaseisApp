package com.example.vaseisapp.ui.dashboard.topicscenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemTopicBinding
import com.example.vaseisapp.domain.topics.Topic
import com.example.vaseisapp.ui.diffutil.TOPIC_ITEM_DIFF_UTIL

class TopicsAdapter(private val listener : TopicListener) : ListAdapter<Topic, TopicsAdapter.TopicsViewHolder>(TOPIC_ITEM_DIFF_UTIL) {

    interface TopicListener {
        fun topicOnClick(topic : Topic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopicBinding.inflate(layoutInflater, parent, false)
        return TopicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class TopicsViewHolder(private val binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(topic: Topic) {
            with(binding) {
                yearTextView.text = topic.year
                Glide.with(root).load(topic.imgSrc).placeholder(R.color.white).into(topicImageView)

                root.setOnClickListener { listener.topicOnClick(topic) }
            }
        }
    }
}
