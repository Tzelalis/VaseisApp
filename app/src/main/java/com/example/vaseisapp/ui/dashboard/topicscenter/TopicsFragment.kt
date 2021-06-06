package com.example.vaseisapp.ui.dashboard.topicscenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentTopicsBinding
import com.example.vaseisapp.domain.topics.Topic
import com.example.vaseisapp.domain.topics.TopicLesson
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsExamsTypeAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.adapters.TopicsTitleAdapter
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import com.example.vaseisapp.utils.adapters.HorizontalConcatAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopicsFragment : BaseFragment<FragmentTopicsBinding>() {

    private val topicsAdapter: ConcatAdapter by lazy { ConcatAdapter() }

    private val viewModel: TopicsViewModel by viewModels()
    //private val viewModel: TopicsViewModel by activityViewModels()
    //private val viewModel: TopicsViewModel by navGraphViewModels(R.id.main_nav_graph)

    private val examTypeAdapter: TopicsExamsTypeAdapter by lazy { TopicsExamsTypeAdapter(examTypeItemListener) }

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
            examTypeUI.observe(viewLifecycleOwner, { examsTypes ->
                examTypeAdapter.submitList(examsTypes)
                binding.topicsRecyclerView.smoothScrollToPosition(examsTypes.indexOfFirst { it.isSelected })
                loadTopicsByExamType(examsTypes.firstOrNull { it.isSelected }?.examType?.id ?: examsTypes[0].examType.id)
            })

            topicLesson.observe(viewLifecycleOwner, { lessons ->
                binding.progressCircular.visibility = View.GONE

                fillData(lessons)
            })

            loadExamTypes()
        }

    }

    private fun fillData(lessons: List<TopicLesson>) {
        if(lessons.isEmpty()){
            binding.noResultsImg.isVisible = true
            return
        }

        binding.noResultsImg.isVisible = false

        for (lesson in lessons) {
            val titleAdapter = TopicsTitleAdapter()
            titleAdapter.submitList(listOf(Topic("", "", lesson.shortName)))
            topicsAdapter.addAdapter(titleAdapter)

            val listener = object : TopicsAdapter.TopicListener {
                override fun topicOnClick(topic: Topic) {
                    //todo check if pdf open without external app
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

    private val examTypeItemListener = object : TopicsExamsTypeAdapter.ExamTypeItemListener {
        override fun onExamTypeItemClickListener(selectedGroup: ExamTypeItem, position: Int) {
            val oldPosition = examTypeAdapter.currentList.indexOfFirst { it.isSelected }

            if (oldPosition == position && oldPosition >= 0)
                return

            viewModel.loadTopicsByExamType(examTypeAdapter.currentList[position].examType.id)
            binding.groupsRecyclerView.smoothScrollToPosition(position)
            examTypeAdapter.currentList[oldPosition].isSelected = false
            examTypeAdapter.currentList[position].isSelected = true
            examTypeAdapter.notifyItemChanged(oldPosition)
            examTypeAdapter.notifyItemChanged(position)

            for (adapter in topicsAdapter.adapters)
                topicsAdapter.removeAdapter(adapter)
            binding.progressCircular.isVisible = true
        }
    }
}