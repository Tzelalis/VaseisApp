package com.vaseis.app.ui.dashboard.departmentcenter.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vaseis.app.R
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentBasesFilterBinding
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.OrderType
import com.vaseis.app.ui.main.MainViewModel
import com.vaseis.app.utils.ConstraintRadioGroup
import com.vaseis.app.utils.setTopMarginForStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasesFilterFragment : BaseFragment<FragmentBasesFilterBinding>() {

    private val viewModel: BaseFilterViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getViewBinding(): FragmentBasesFilterBinding = FragmentBasesFilterBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            root.setTopMarginForStatusBar()

            closeImg.setOnClickListener { findNavController().navigateUp() }
            /* baseFilterFromTextview.text = baseFilterSlider.values[0].toInt().toString()
             baseFilterToTextview.text = baseFilterSlider.values[1].toInt().toString()

             baseFilterSlider.addOnChangeListener(RangeSlider.OnChangeListener { slider, _, _ ->
                 baseFilterFromTextview.text = slider.values[0].toInt().toString()
                 baseFilterToTextview.text = slider.values[1].toInt().toString()
             })*/

            enableDepartmentsCheckbox.setOnCheckedChangeListener { _, isChecked ->
                mainViewModel.setDisabledDepartments(isChecked)
            }

            orderRg.setOnCheckedChangeListener(
                object : ConstraintRadioGroup.OnCheckedChangeListener {
                    override fun onCheckedChanged(group: ConstraintRadioGroup?, checkedId: Int) {
                        when (checkedId) {
                            alphabeticRadiobutton.id -> mainViewModel.setOrder(OrderType.ALPHABETICAL)
                            universityRadiobutton.id -> mainViewModel.setOrder(OrderType.UNIVERSITY)
                        }
                    }
                }
            )

            fieldsLayout.setOnClickListener {
                val action = BasesFilterFragmentDirections.actionBasesFilterFragmentToBasesFilterFieldsFragment()
                findNavController().safeNavigate(action, R.id.basesFilterFragment)
            }

            enableDepartmentsLayout.setOnClickListener {
                enableDepartmentsCheckbox.isChecked = !enableDepartmentsCheckbox.isChecked
            }

            universityLayout.setOnClickListener {
                val action = BasesFilterFragmentDirections.actionBasesFilterFragment2ToBasesFilterUniversitiesFragment()
                findNavController().safeNavigate(action, R.id.basesFilterFragment)
            }

            cityLayout.setOnClickListener {
                val action = BasesFilterFragmentDirections.actionBasesFilterFragmentToBasesFilterExamType()
                findNavController().safeNavigate(action, R.id.basesFilterFragment)
            }

            confirmBtn.setOnClickListener {
                val orderType = when (orderRg.checkedRadioButtonId) {
                    R.id.university_radiobutton -> OrderType.UNIVERSITY
                    else -> OrderType.ALPHABETICAL
                }

                findNavController().navigateUp()

                mainViewModel.setFilter()
            }

            clearBtn.setOnClickListener {
                with(binding) {
                    alphabeticRadiobutton.isChecked = true
                    enableDepartmentsCheckbox.isChecked = false
                    universitiesListTextview.text = ""
                }
                mainViewModel.clearFilter()
                mainViewModel.clearTempFilter()
            }
        }
    }

    private fun setupObservers() {
        with(mainViewModel) {
            filterSavedState.observe(viewLifecycleOwner, { filter ->
                binding.enableDepartmentsCheckbox.isChecked = filter?.showDisabledDepartments == true

                when (filter?.order) {
                    OrderType.ALPHABETICAL -> binding.alphabeticRadiobutton.isChecked = true
                    OrderType.UNIVERSITY -> binding.universityRadiobutton.isChecked = true
                }
            })

            tempFilterSavedState.observe(viewLifecycleOwner, {
                if (it.universities.isNotEmpty())
                    binding.universitiesListTextview.text = getString(R.string.bases_filter_universities_count, it.universities.size.toString())

                for(i in it.fields.indices) {
                    binding.fieldsListTxt.text = binding.fieldsListTxt.text.toString() + it.fields[i].key

                    if(i < it.fields.size - 1)
                        binding.fieldsListTxt.text = binding.fieldsListTxt.text.toString() + ", "
                }

                binding.examTypeListTextview.text = it.examType.shortName
            })

            initTempFilter()
        }
    }
}