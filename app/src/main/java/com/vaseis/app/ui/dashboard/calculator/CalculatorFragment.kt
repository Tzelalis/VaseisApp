package com.vaseis.app.ui.dashboard.calculator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentCalculatorBinding
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.vaseis.app.ui.dashboard.calculator.adapter.ExamTypeViewPagerAdapter
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

    private fun setupViewPagerWithTabs(calculatorExamTypes: List<CalculatorExamType>) {
        with(binding) {
            if (groupsViewPager.adapter == null) {   //viewpager keep adapter (for some reason) after change fragment
                val list = mutableListOf<Unit>()
                for(examType in calculatorExamTypes)
                    list.add(Unit)

                groupsViewPager.adapter = ExamTypeViewPagerAdapter(this@CalculatorFragment, list)
                TabLayoutMediator(examsTypeTabLayout, groupsViewPager) { tab, position ->
                    tab.text = calculatorExamTypes[position].shortName
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