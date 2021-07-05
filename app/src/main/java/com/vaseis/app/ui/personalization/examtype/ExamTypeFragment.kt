package com.vaseis.app.ui.personalization.examtype

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentExamTypeBinding
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty
import com.vaseis.app.ui.dashboard.topicscenter.model.ExamTypeItem
import com.vaseis.app.utils.setTopMarginForStatusBar
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                adapter.currentList.firstOrNull { item -> item.isSelected }?.examType?.let { group ->
                    viewModel.saveTypes(PrefProperty(group.id, group.shortName))
                }

                isEnabled = false
                activity?.onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setupViews() {
        with(binding) {
            root.setTopMarginForStatusBar()

            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }

            categoryRecyclerView.adapter = adapter

            proceedButton.setOnClickListener {
                adapter.currentList.firstOrNull { item -> item.isSelected }?.examType?.let { group ->
                    viewModel.saveTypes(PrefProperty(group.id, group.shortName))
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
            })

            loadTypes()
        }
    }
}