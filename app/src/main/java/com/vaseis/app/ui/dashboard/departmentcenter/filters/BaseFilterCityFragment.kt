package com.vaseis.app.ui.dashboard.departmentcenter.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentBaseFilterCitiesBinding
import com.vaseis.app.ui.dashboard.departmentcenter.filters.adapters.BaseFilterCityAdapter
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.CityFilterItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BaseFilterCityFragment : BaseFragment<FragmentBaseFilterCitiesBinding>() {

    private val viewModel: BaseFilterViewModel by viewModels()
    private val adapter: BaseFilterCityAdapter by lazy { BaseFilterCityAdapter(listener) }

    override fun getViewBinding(): FragmentBaseFilterCitiesBinding = FragmentBaseFilterCitiesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            citiesRecyclerview.addItemDecoration(DividerItemDecoration(citiesRecyclerview.context, DividerItemDecoration.VERTICAL))
            citiesRecyclerview.adapter = adapter
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            citiesUI.observe(viewLifecycleOwner, { cities ->
                val list = cities.toMutableList()
                list.add(CityFilterItem("", false))
                adapter.submitList(list)
            })

            loadCities()
        }
    }

    private val listener = object : BaseFilterCityAdapter.FilterCityListener {
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
