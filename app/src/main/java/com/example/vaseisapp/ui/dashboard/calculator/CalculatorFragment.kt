package com.example.vaseisapp.ui.dashboard.calculator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentCalculatorBinding
import com.example.vaseisapp.domain.calculation.entities.CalculatorExamType
import com.example.vaseisapp.ui.dashboard.calculator.adapter.ExamTypeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculatorFragment : BaseFragment<FragmentCalculatorBinding>() {

    private val viewModel: CalculatorViewModel by activityViewModels()

    override fun getViewBinding(): FragmentCalculatorBinding = FragmentCalculatorBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservers()
    }

    override fun onStop() {
        super.onStop()
        binding.groupsViewPager.adapter = null
    }

    private fun setupView() {
        with(binding) {
            groupsViewPager.offscreenPageLimit = 1
            groupsViewPager.isUserInputEnabled = false
        }
    }

    private fun setupViewPagerWithTabs(examsTypeCalculators: List<CalculatorExamType>) {
        with(binding) {
            if (groupsViewPager.adapter == null) {   //viewpager keep adapter (for some reason) after change fragment
                groupsViewPager.adapter = ExamTypeViewPagerAdapter(this@CalculatorFragment, examsTypeCalculators)
                TabLayoutMediator(examsTypeTabLayout, groupsViewPager) { tab, position ->
                    tab.text = examsTypeCalculators[position].short_name
                }.attach()
            }
        }
    }


    private fun setupObservers() {
        with(viewModel) {
            examsTypes.observe(viewLifecycleOwner) { listOfExamTypes ->
                setupViewPagerWithTabs(listOfExamTypes)
            }
            examTypePref.observe(viewLifecycleOwner) { pref ->
                binding.groupsViewPager.setCurrentItem(pref, true)
            }
            resultUI.observe(viewLifecycleOwner, { result ->
                binding.resultTextView.text = result
            })

            loadData()
        }
    }
}