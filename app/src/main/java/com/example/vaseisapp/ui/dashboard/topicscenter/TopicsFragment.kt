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
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsExamsTypeAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsTitleAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import com.example.vaseisapp.ui.personalization.examtype.ExamTypeListAdapter
import com.example.vaseisapp.utils.adapters.HorizontalConcatAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopicsFragment : BaseFragment<FragmentTopicsBinding>() {

    private val topicsAdapter: ConcatAdapter by lazy { ConcatAdapter() }
    private val viewModel: TopicsViewModel by viewModels()


    private val examTypeAdapter: TopicsExamsTypeAdapter by lazy { TopicsExamsTypeAdapter(examTypeItemListener) }

    private val examTypeItemListener = object : TopicsExamsTypeAdapter.ExamTypeItemListener {

        override fun onExamTypeItemClickListener(selectedGroup: ExamTypeItem, position: Int) {
            binding.groupsRecyclerView.smoothScrollToPosition(position)
        }

    }

    override fun getViewBinding(): FragmentTopicsBinding = FragmentTopicsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.topicsRecyclerView.adapter = topicsAdapter
        binding.groupsRecyclerView.adapter = examTypeAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.groupsRecyclerView)

    }

    private fun setupObservers() {
        with(viewModel) {
            topicLesson.observe(viewLifecycleOwner, { lessons ->
                fillData(lessons)
            })
            examTypeUI.observe(viewLifecycleOwner, { examsTypes ->
                examTypeAdapter.submitList(examsTypes)
            })

            loadGroups()
            loadTopicLesons()
        }
    }

    private fun fillData(lessons: List<TopicLesson>) {
        for (lesson in lessons) {
            val titleAdapter = TopicsTitleAdapter()
            titleAdapter.submitList(listOf(Topic("", lesson.lesson, "")))
            topicsAdapter.addAdapter(titleAdapter)

            val listener = object : TopicsAdapter.TopicListener {
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
}