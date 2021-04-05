package com.example.vaseisapp.ui.dashboard.calculator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentCalculatorBinding
import com.example.vaseisapp.domain.calculation.entities.ExamType
import com.example.vaseisapp.ui.dashboard.calculator.adapter.ExamTypeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculatorFragment : BaseFragment<FragmentCalculatorBinding>() {

    private val viewModel: CalculatorViewModel by activityViewModels()


    override fun getViewBinding(): FragmentCalculatorBinding = FragmentCalculatorBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupViews(examsTypes: List<ExamType>) {
        with(binding) {
            groupsViewPager.isUserInputEnabled = false
            groupsViewPager.adapter = ExamTypeViewPagerAdapter(this@CalculatorFragment, examsTypes)
            TabLayoutMediator(examsTypeTabLayout, groupsViewPager) { tab, position ->
                tab.text = examsTypes[position].short_name
            }
        }.attach()
    }


    private fun setupObservers() {
        with(viewModel) {
            examsTypes.observe(viewLifecycleOwner) { listOfExamTypes ->
                setupViews(listOfExamTypes)
            }

            examTypePref.observe(viewLifecycleOwner) { pref ->
                examsTypes.value?.let { list ->
                    binding.groupsViewPager.currentItem = list.indexOfFirst { examType -> examType.id == pref }
                }
            }

            resultUI.observe(viewLifecycleOwner, { result ->
                binding.resultTextView.text = result
            })

            loadData()
        }
    }
}