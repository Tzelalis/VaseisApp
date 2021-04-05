package com.example.vaseisapp.ui.personalization.examtype

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentExamTypeBinding
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExamTypeFragment : BaseFragment<FragmentExamTypeBinding>() {

    private val viewModel: ExamTypeViewModel by viewModels()

    private val adapter: ExamTypeListAdapter by lazy { ExamTypeListAdapter(listener) }
    private val listener = object : ExamTypeListAdapter.ExamTypeClickListener {
        override fun onClickListener(item: ExamTypeItem) {
        }

    }

    override fun getViewBinding(): FragmentExamTypeBinding = FragmentExamTypeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }

            categoryRecyclerView.adapter = adapter

            proceedButton.setOnClickListener {
                adapter.currentList.firstOrNull { item -> item.isSelected }?.examType?.let { group ->
                    viewModel.saveTypes(PrefProperty(group.id, group.short_name))
                }
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            typesUI.observe(viewLifecycleOwner, { types ->
                adapter.submitList(types)
            })

            savedUI.observe(viewLifecycleOwner, {
                activity?.onBackPressed()
            })

            loadTypes()
        }
    }
}