package com.vaseis.app.ui.dashboard.calculator.group

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentCalculatorGroupBinding
import com.vaseis.app.domain.calculation.entities.CalculatorGroup
import com.vaseis.app.ui.dashboard.calculator.CalculatorViewModel
import com.vaseis.app.ui.dashboard.calculator.adapter.GroupAdapter
import com.vaseis.app.ui.dashboard.calculator.adapter.LessonAdapter
import com.vaseis.app.ui.dashboard.calculator.model.GroupItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculatorGroupFragment : BaseFragment<FragmentCalculatorGroupBinding>() {

    companion object {
        const val GROUP_KEY = "GROUP_KEY"
    }

    private val calculatorViewModel: CalculatorViewModel by activityViewModels()
    private val viewModel: CalculatorGroupViewModel by viewModels()

    private val lessonsAdapter: LessonAdapter by lazy { LessonAdapter(lessonListener) }
    private val lessonListener = object : LessonAdapter.LessonListener {
        override fun onLessonTextChanged() {
            setResultDegree()
        }
    }

    private val groupAdapter: GroupAdapter by lazy { GroupAdapter(groupListener) }
    private val groupListener = object : GroupAdapter.GroupListener {
        override fun onGroupClickListener(selectedGroup: GroupItem, position: Int) {
            binding.groupsRecyclerView.smoothScrollToPosition(position)
            viewModel.groupsUI.value?.get(position)?.calculatorGroup?.lessons?.let { viewModel.loadLessons(it) }

            setResultDegree()
        }
    }

    override fun getViewBinding(): FragmentCalculatorGroupBinding = FragmentCalculatorGroupBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        arguments?.getParcelableArrayList<CalculatorGroup>(GROUP_KEY)?.let { viewModel.loadGroups((it)) }
    }

    override fun onResume() {
        super.onResume()
        setResultDegree()
    }

    private fun setupViews() {
        with(binding) {
            lessonsRecyclerView.adapter = lessonsAdapter
            lessonsRecyclerView.itemAnimator = null
            groupsRecyclerView.adapter = groupAdapter
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            groupsUI.observe(viewLifecycleOwner, { groupItems ->
                fillData(groupItems)
            })

            lessonsUI.observe(viewLifecycleOwner, { lessonItems ->
                lessonsAdapter.submitList(lessonItems.filter { it.calculatorLesson.isMandatory })
            })
        }

        setVisibleTimer()
    }

    private fun fillData(groups: List<GroupItem>) {
        groupAdapter.submitList(groups)

        val index = groups.indexOf((groups.firstOrNull { it.isSelected } ?: groups[0]))
        viewModel.loadLessons(groups[index].calculatorGroup.lessons)
    }

    private fun setResultDegree() {
        var result = 0.0
        for (i in 0 until lessonsAdapter.currentList.size) {
            result += (lessonsAdapter.currentList[i].degree.toDoubleOrNull() ?: 0.0) * lessonsAdapter.currentList[i].calculatorLesson.weight
        }

        calculatorViewModel.changeResult(result.toInt().toString())
    }

    private fun setVisibleTimer() {
        with(binding) {
            root.isVisible = true
        }
    }
}