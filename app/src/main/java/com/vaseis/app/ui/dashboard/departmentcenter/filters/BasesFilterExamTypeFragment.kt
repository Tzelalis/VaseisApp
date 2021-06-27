package com.vaseis.app.ui.dashboard.departmentcenter.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentBasesFilterExamTypeBinding
import com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters.BasesFilterExamTypeAdapter
import com.vaseis.app.ui.main.MainViewModel
import com.vaseis.app.utils.setTopMarginForStatusBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasesFilterExamTypeFragment : BaseFragment<FragmentBasesFilterExamTypeBinding>() {
    private val viewModel: BaseFilterViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val adapter: BasesFilterExamTypeAdapter by lazy { BasesFilterExamTypeAdapter() }

    override fun getViewBinding(): FragmentBasesFilterExamTypeBinding = FragmentBasesFilterExamTypeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            root.setTopMarginForStatusBar()
            titleTxt.text = "Τύπος εξετάσεων"
            examTypeRv.adapter = adapter
            examTypeRv.addItemDecoration(DividerItemDecoration(examTypeRv.context, LinearLayoutManager.VERTICAL))
            backButtonImg.backButtonImageView.setOnClickListener { findNavController().navigateUp() }

            confirmBtn.setOnClickListener {
                mainViewModel.setExamType(adapter.currentList.firstOrNull { it.isSelected }?.examType ?: adapter.currentList[0].examType)
                findNavController().navigateUp()
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            examTypeItemUI.observe(viewLifecycleOwner, { examTypes ->
                (examTypes.firstOrNull { it.examType.filter == mainViewModel.filterSavedState.value?.examType?.filter } ?:  examTypes[0]).isSelected = true
                adapter.submitList(examTypes)
            })

            loadExamTypes()
        }
    }
}