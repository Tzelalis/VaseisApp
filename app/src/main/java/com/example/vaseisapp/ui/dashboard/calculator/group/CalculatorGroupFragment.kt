package com.example.vaseisapp.ui.dashboard.calculator.group

import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentCalculatorGroupBinding
import com.example.vaseisapp.domain.calculation.entities.Group
import com.example.vaseisapp.ui.dashboard.calculator.CalculatorViewModel
import com.example.vaseisapp.ui.dashboard.calculator.adapter.GroupAdapter
import com.example.vaseisapp.ui.dashboard.calculator.adapter.LessonAdapter
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_lessons.view.*


@AndroidEntryPoint
class CalculatorGroupFragment : BaseFragment<FragmentCalculatorGroupBinding>() {

    companion object {
        const val GROUP_KEY = "GROUP_KEY"
    }

    private val viewModel: CalculatorViewModel by activityViewModels()
    private val groupViewModel: CalculatorGroupViewModel by viewModels()

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

            lessonsAdapter.submitList(null)
            lessonsAdapter.notifyDataSetChanged()
            val newList = ArrayList(arguments?.getParcelableArrayList<Group>(GROUP_KEY)?.get(position)?.mandatoryLessons?.toList())
            lessonsAdapter.submitList(newList)
            lessonsAdapter.notifyDataSetChanged()

            setResultDegree()
        }
    }

    override fun getViewBinding(): FragmentCalculatorGroupBinding = FragmentCalculatorGroupBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        arguments?.getParcelableArrayList<Group>(GROUP_KEY)?.let { groupViewModel.loadGroups((it)) }
    }

    override fun onResume() {
        super.onResume()
        setResultDegree()
    }

    private fun setupViews() {
        with(binding) {
            lessonsRecyclerView.adapter = lessonsAdapter
            groupsRecyclerView.adapter = groupAdapter
        }
    }

    private fun setupObservers() {
        with(groupViewModel) {
            groupsUI.observe(viewLifecycleOwner, { items ->
                fillData(items)
            })
        }
    }

    private fun fillData(groups: List<GroupItem>) {
        groupAdapter.submitList(groups)
        lessonsAdapter.submitList(groups[0].group.mandatoryLessons)
    }

    private fun setResultDegree() {
        var result = 0.0
        for (i in 0 until binding.lessonsRecyclerView.size) {
            val degree = binding.lessonsRecyclerView[i].lessons_number_picker.binding.lessonEdittext.text.toString().toDoubleOrNull() ?: 0.0
            val gravity = lessonsAdapter.currentList[i].gravity
            result += degree * gravity
        }

        viewModel.changeResult(result.toInt().toString())
    }
}