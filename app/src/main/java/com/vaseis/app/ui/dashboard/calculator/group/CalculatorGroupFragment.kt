package com.vaseis.app.ui.dashboard.calculator.group

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentCalculatorGroupBinding
import com.vaseis.app.domain.calculation.entities.CalculatorGroup
import com.vaseis.app.ui.dashboard.calculator.CalculatorViewModel
import com.vaseis.app.ui.dashboard.calculator.adapter.GroupAdapter
import com.vaseis.app.ui.dashboard.calculator.adapter.HorizontalLessonsAdapter
import com.vaseis.app.ui.dashboard.calculator.adapter.LessonAdapter
import com.vaseis.app.ui.dashboard.calculator.model.GroupItem
import com.vaseis.app.ui.dashboard.calculator.model.LessonItem
import com.vaseis.app.utils.centersnap.SnapOnScrollListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculatorGroupFragment : BaseFragment<FragmentCalculatorGroupBinding>() {

    companion object {
        const val GROUP_KEY = "GROUP_KEY"
    }

    private val calculatorViewModel: CalculatorViewModel by activityViewModels()
    private val viewModel: CalculatorGroupViewModel by viewModels()
    private val concatAdapter: ConcatAdapter by lazy { ConcatAdapter() }
    private val snapHelper: SnapHelper by lazy { PagerSnapHelper() }

    private val groupAdapter: GroupAdapter by lazy { GroupAdapter(groupListener) }
    private val groupListener = object : GroupAdapter.GroupListener {
        override fun onGroupClickListener(selectedGroup: GroupItem, position: Int) {
            binding.groupsRecyclerView.smoothScrollToPosition(position)
            binding.lessonsRecyclerView.smoothScrollToPosition(position)

            //setResultDegree()
        }
    }

    override fun getViewBinding(): FragmentCalculatorGroupBinding = FragmentCalculatorGroupBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        //arguments?.getParcelableArrayList<CalculatorGroup>(GROUP_KEY)?.let { viewModel.loadGroups((it)) }
    }

    override fun onResume() {
        super.onResume()
        setResultDegree()
    }

    private fun setupViews() {
        with(binding) {
            lessonsRecyclerView.adapter = concatAdapter
            snapHelper.attachToRecyclerView(lessonsRecyclerView)

            lessonsRecyclerView.addOnScrollListener(
                SnapOnScrollListener(snapHelper,
                    SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    object : SnapOnScrollListener.OnSnapPositionChangeListener {
                        override fun onSnapPositionChange(position: Int, oldPosition: Int) {
                            if (position == -1)
                                return

                            groupAdapter.currentList.firstOrNull { it.isSelected }?.isSelected = false
                            groupAdapter.currentList[position].isSelected = true
                            binding.groupsRecyclerView.smoothScrollToPosition(position)
                            groupAdapter.notifyItemChanged(position)

                            if (oldPosition != -1)
                                groupAdapter.notifyItemChanged(oldPosition)

                            setResultDegree()
                        }
                    })
            )

            groupsRecyclerView.adapter = groupAdapter
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            groupsUI.observe(viewLifecycleOwner, { groupItems ->
                fillData(groupItems)
            })
        }
        calculatorViewModel.examsTypes.observe(viewLifecycleOwner, { examList ->
            arguments?.getInt(GROUP_KEY, 0)?.let { viewModel.loadGroups((examList[it].groups)) }
        })
    }

    private fun fillData(groups: List<GroupItem>) {
        groupAdapter.submitList(groups)

        for (group in groups) {
            val list = mutableListOf<LessonItem>()
            for (lesson in group.calculatorGroup.lessons)
                list.add(LessonItem(lesson, "0"))

            val lessonListener = object : LessonAdapter.LessonListener {
                override fun onLessonTextChanged() {
                    setResultDegree()
                }
            }

            val adapter = LessonAdapter(lessonListener)
            adapter.submitList(list.filter { it.calculatorLesson.isMandatory })
            concatAdapter.addAdapter(HorizontalLessonsAdapter(requireContext(), adapter))
        }
    }

    private fun setResultDegree() {
        var result = 0.0
        val position = (binding.lessonsRecyclerView.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition() ?: return

        if (position == -1)
            return

        val lessonAdapter = ((concatAdapter.adapters[position]) as? HorizontalLessonsAdapter)?.getAdapter() ?: return
        for (i in 0 until lessonAdapter.currentList.size) {
            result += (lessonAdapter.currentList[i].degree.toDoubleOrNull() ?: 0.0) * lessonAdapter.currentList[i].calculatorLesson.weight
        }

        calculatorViewModel.changeResult(result.toInt().toString())
    }
}