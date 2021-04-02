package com.example.vaseisapp.ui.dashboard.topicscenter.adapters

import android.graphics.drawable.ShapeDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ItemTopicBinding
import com.example.vaseisapp.domain.topics.Topic
import com.example.vaseisapp.ui.diffutil.TOPIC_ITEM_DIFF_UTIL
import com.example.vaseisapp.utils.views.CutCornerShape

class TopicsAdapter(private val listener: TopicListener) : ListAdapter<Topic, TopicsAdapter.TopicsViewHolder>(TOPIC_ITEM_DIFF_UTIL) {

    interface TopicListener {
        fun topicOnClick(topic: Topic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopicBinding.inflate(layoutInflater, parent, false)
        return TopicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        if (position == 0)
            holder.bindFirstTo(getItem(position))
        else if (position == itemCount - 1)
            holder.bindLastTo(getItem(position))
        else
            holder.bindTo(getItem(position))
    }

    inner class TopicsViewHolder(private val binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(topic: Topic) {
            with(binding) {
                yearTextView.text = topic.year
                Glide.with(root).load(topic.imgSrc).placeholder(R.color.white).into(topicImageView)
                root.setOnClickListener { listener.topicOnClick(topic) }
                yearTextView.background = ShapeDrawable(CutCornerShape())
            }
        }

        fun bindFirstTo(topic: Topic) {
            with(binding) {
                val marginParams = root.layoutParams as? ViewGroup.MarginLayoutParams
                marginParams?.rightMargin = root.resources.getDimensionPixelSize(R.dimen.margin_8dp)
                marginParams?.marginStart = root.resources.getDimensionPixelSize(R.dimen.margin_16dp)
                root.layoutParams = marginParams
            }
            bindTo(topic)
        }

        fun bindLastTo(topic: Topic) {
            with(binding.root) {
                val marginParams = layoutParams as? ViewGroup.MarginLayoutParams
                marginParams?.rightMargin = resources.getDimensionPixelSize(R.dimen.margin_16dp)
                layoutParams = marginParams
            }
            bindTo(topic)
        }
    }
}
