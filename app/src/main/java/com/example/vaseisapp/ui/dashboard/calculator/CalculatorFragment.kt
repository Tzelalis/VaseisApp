package com.example.vaseisapp.ui.dashboard.calculator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentCalculatorBinding
import com.example.vaseisapp.domain.calculation.entities.CalculationEntity
import com.example.vaseisapp.ui.dashboard.calculator.adapter.ExamTypeAdapter
import com.example.vaseisapp.ui.dashboard.calculator.adapter.LessonAdapter
import com.example.vaseisapp.ui.dashboard.calculator.adapter.GroupAdapter
import com.example.vaseisapp.ui.dashboard.calculator.model.ExamTypeItem
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculatorFragment : BaseFragment<FragmentCalculatorBinding>(), GroupAdapter.GroupListener {
    private val lessonsAdapter: LessonAdapter by lazy { LessonAdapter() }

    private val viewModel: CalculatorViewModel by viewModels()
    private val groupAdapter: GroupAdapter by lazy { GroupAdapter(groupListener) }
    private val examTypeAdapter: ExamTypeAdapter by lazy { ExamTypeAdapter(examTypeListener) }

    private val examTypeListener = object : ExamTypeAdapter.ExamTypeListener{
        override fun onExamTypeListener(item: ExamTypeItem, position: Int) {
            binding.examsTypeRecyclerView.smoothScrollToPosition(position)

            val nameOfGroups = mutableListOf<GroupItem>()
            viewModel.dataUI.value?.examTypes?.get(position)?.groups?.get(0)?.let { nameOfGroups.add(GroupItem(it.shortName, true)) }
            for (i in 1 until viewModel.dataUI.value?.examTypes?.get(position)?.groups?.size!!) {
                viewModel.dataUI.value?.examTypes?.get(position)?.groups?.get(i)?.let { nameOfGroups.add(GroupItem(it.shortName, false)) }
            }
            groupAdapter.submitList(nameOfGroups)
        }
    }

    private val groupListener = object : GroupAdapter.GroupListener{
        override fun onGroupClickListener(selectedGroup: GroupItem, position: Int) {
            binding.groupsRecyclerView.smoothScrollToPosition(position)
            lessonsAdapter.submitList(examTypeAdapter.currentList.first { it.isSelected }.examType.groups[position].mandatoryLessons)
        }
    }


    override fun getViewBinding(): FragmentCalculatorBinding = FragmentCalculatorBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            lessonsRecyclerView.adapter = lessonsAdapter
            groupsRecyclerView.adapter = groupAdapter
            examsTypeRecyclerView.adapter = examTypeAdapter

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(groupsRecyclerView)

        }
    }

    private fun setupObservers() {
        with(viewModel) {
            dataUI.observe(viewLifecycleOwner, { calculation ->
                fillData(calculation)
            })

            loadLessons()
        }
    }

    private fun fillData(calculation: CalculationEntity) {
        val itemsOfExamTypes = mutableListOf<ExamTypeItem>()
        itemsOfExamTypes.add(ExamTypeItem(calculation.examTypes[0], true))
        for(i in 1 until calculation.examTypes.size){
            itemsOfExamTypes.add(ExamTypeItem(calculation.examTypes[i], false))
        }
        examTypeAdapter.submitList(itemsOfExamTypes)

        lessonsAdapter.submitList(calculation.examTypes[0].groups[0].mandatoryLessons)

        val nameOfGroups = mutableListOf<GroupItem>()
        nameOfGroups.add(GroupItem(calculation.examTypes[0].groups[0].shortName, true))
        for (i in 1 until calculation.examTypes[0].groups.size) {
            nameOfGroups.add(GroupItem(calculation.examTypes[0].groups[i].shortName, false))
        }

        groupAdapter.submitList(nameOfGroups)

    }

    override fun onGroupClickListener(selectedGroup: GroupItem, position: Int) {
        binding.groupsRecyclerView.smoothScrollToPosition(position)

        lessonsAdapter.submitList(viewModel.dataUI.value?.examTypes?.get(0)?.groups?.get(position)?.mandatoryLessons)
    }
}