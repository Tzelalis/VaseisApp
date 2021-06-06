package com.example.vaseisapp.ui.dashboard.departmentcenter.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentBasesFilterUniversitiesBinding
import com.example.vaseisapp.domain.entities.University
import com.example.vaseisapp.ui.dashboard.departmentcenter.filters.adapters.BaseFilterUniversityAdapter
import com.example.vaseisapp.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasesFilterUniversitiesFragment : BaseFragment<FragmentBasesFilterUniversitiesBinding>() {

    private val viewModel: BaseFilterViewModel by viewModels()
    private val adapter: BaseFilterUniversityAdapter by lazy { BaseFilterUniversityAdapter(listener) }

    override fun getViewBinding(): FragmentBasesFilterUniversitiesBinding = FragmentBasesFilterUniversitiesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            universitiesRecyclerView.addItemDecoration(DividerItemDecoration(universitiesRecyclerView.context, DividerItemDecoration.VERTICAL))
            universitiesRecyclerView.adapter = adapter
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            universityUI.observe(viewLifecycleOwner, { universities ->
                val list = universities.toMutableList()
                list.add(0, UniversityFilterItem(University("", "", ""), false))
                adapter.submitList(list)
            })

            loadUniversities()
        }
    }

    private val listener = object : BaseFilterUniversityAdapter.FilterUniversityListener {
        override fun selectAllClickListener() {
            var flag = true
            for (item in adapter.currentList) {
                if (item.isSelected) {
                    flag = false
                    break
                }
            }

            for (item in adapter.currentList) {
                item.isSelected = flag
            }

            adapter.notifyDataSetChanged()
        }
    }
}
