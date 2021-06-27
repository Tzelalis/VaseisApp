package com.vaseis.app.ui.dashboard.departmentcenter.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentBasesFilterUniversitiesBinding
import com.vaseis.app.domain.entities.University
import com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters.BaseFilterUniversityAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.vaseis.app.ui.main.MainViewModel
import com.vaseis.app.utils.setTopMarginForStatusBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasesFilterUniversitiesFragment : BaseFragment<FragmentBasesFilterUniversitiesBinding>() {

    private val viewModel: BaseFilterViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val adapter: BaseFilterUniversityAdapter by lazy { BaseFilterUniversityAdapter(listener) }

    override fun getViewBinding(): FragmentBasesFilterUniversitiesBinding = FragmentBasesFilterUniversitiesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            backButtonImg.backButtonImageView.setOnClickListener { findNavController().navigateUp() }
            titleTxt.text = "Ιδρύματα"
            root.setTopMarginForStatusBar()
            universitiesRecyclerView.addItemDecoration(DividerItemDecoration(universitiesRecyclerView.context, DividerItemDecoration.VERTICAL))
            universitiesRecyclerView.adapter = adapter

            confirmBtn.setOnClickListener {
                val universitiesId = mutableListOf<String>()
                for (uni in adapter.currentList.filter { it.isSelected })
                    universitiesId.add(uni.university.id)

                mainViewModel.setUniversities(universitiesId)
                findNavController().navigateUp()
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            universityUI.observe(viewLifecycleOwner, { universities ->
                if (mainViewModel.tempFilterSavedState.value?.universities?.size == universities.size) {
                    for (item in universities)
                        item.isSelected = true
                } else {
                    for (selectedUni in mainViewModel.tempFilterSavedState.value?.universities ?: listOf()) {
                        for (uni in universities)
                            if (uni.university.id == selectedUni) {
                                uni.isSelected = true
                                break
                            }
                    }
                }

                (universities.toMutableList()).add(0, UniversityFilterItem(University("", "", ""), false))
                adapter.submitList(universities)
            })

            loadUniversities()
        }
    }

    private val listener = object : BaseFilterUniversityAdapter.FilterUniversityListener {
        override fun selectAllClickListener() {
            val flag = adapter.currentList.filter { it.isSelected }.size != adapter.currentList.size
            for (item in adapter.currentList) {
                item.isSelected = flag
            }

            adapter.notifyDataSetChanged()
        }
    }
}
