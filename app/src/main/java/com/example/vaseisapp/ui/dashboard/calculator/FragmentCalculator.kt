package com.example.vaseisapp.ui.dashboard.calculator

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentCalculatorBinding
import com.example.vaseisapp.domain.calculation.entities.CalculationEntity
import com.example.vaseisapp.domain.calculation.entities.Lesson
import com.example.vaseisapp.ui.dashboard.calculator.adapter.LessonAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll


@AndroidEntryPoint
class FragmentCalculator : BaseFragment<FragmentCalculatorBinding>() {
    private val lessonsAdapter : LessonAdapter by lazy { LessonAdapter() }

    private val viewModel : CalculatorViewModel by viewModels()

    override fun getViewBinding(): FragmentCalculatorBinding = FragmentCalculatorBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews()    {
        with(binding)   {
            lessonsRecyclerView.adapter = lessonsAdapter

            groupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val spinner = view as? Spinner

                    if(spinner?.selectedItemPosition != position){
                        lessonsAdapter.submitList(null)
                        lessonsAdapter.submitList(viewModel.dataUI.value?.group?.get(position)?.mandatoryLessons)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
        }
    }

    private fun setupObservers()    {
        with(viewModel) {
            dataUI.observe(viewLifecycleOwner, { calculation ->
                fillData(calculation)
            })

            loadLessons()
        }
    }

    private fun fillData(calculation: CalculationEntity)  {
        lessonsAdapter.submitList(calculation.group[0].mandatoryLessons)

        val nameOfGroups = mutableListOf<String>()
        for(group in calculation.group){
            nameOfGroups.add(group.shortName)
        }

        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_item, nameOfGroups.toTypedArray())
        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.groupSpinner.adapter = spinnerArrayAdapter
    }
}