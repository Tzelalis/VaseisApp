package com.example.vaseisapp.ui.dashboard.topicscenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentTopicsBinding
import com.example.vaseisapp.domain.topics.Topic
import com.example.vaseisapp.domain.topics.TopicLesson
import com.example.vaseisapp.utils.adapters.HorizontalConcatAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsTitleAdapter
import com.example.vaseisapp.ui.dashboard.calculator.adapter.GroupAdapter
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopicsFragment : BaseFragment<FragmentTopicsBinding>(), GroupAdapter.GroupListener {

    private val topicsAdapter: ConcatAdapter by lazy { ConcatAdapter() }
    private val viewModel: TopicsViewModel by viewModels()


    private val groupAdapter: GroupAdapter by lazy { GroupAdapter(this) }

    override fun getViewBinding(): FragmentTopicsBinding = FragmentTopicsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupViews() {
        binding.topicsRecyclerView.adapter = topicsAdapter
        binding.groupsRecyclerView.adapter = groupAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.groupsRecyclerView)

    }

    private fun setupObservers() {
        with(viewModel) {
            topicLesson.observe(viewLifecycleOwner, { lessons ->
                fillData(lessons)
            })
            groupUI.observe(viewLifecycleOwner, { groups ->
                groupAdapter.submitList(groups)
            })

            loadGroups()
            loadTopicLesons()
        }
    }

    private fun fillData(lessons: List<TopicLesson>)  {
        for(lesson in lessons)  {
            val titleAdapter = TopicsTitleAdapter()
            titleAdapter.submitList(listOf(Topic("", lesson.lesson, "")))
            topicsAdapter.addAdapter(titleAdapter)

            val listener = object : TopicsAdapter.TopicListener{
                override fun topicOnClick(topic: Topic) {
                    //todo open pdf in app

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(topic.pdfUrl), "application/pdf")
                    intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                    startActivity(intent)
                }

            }
            val adapter = TopicsAdapter(listener)
            adapter.submitList(lesson.topics)
            topicsAdapter.addAdapter(HorizontalConcatAdapter(requireContext(), adapter))
        }
    }

    override fun onGroupClickListener(selectedGroup: GroupItem, position: Int) {
        binding.groupsRecyclerView.smoothScrollToPosition(position)
    }
}