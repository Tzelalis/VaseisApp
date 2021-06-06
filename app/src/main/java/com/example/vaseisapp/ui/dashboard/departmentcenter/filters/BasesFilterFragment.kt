package com.example.vaseisapp.ui.dashboard.departmentcenter.filters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentBasesFilterBinding
import com.google.android.material.slider.RangeSlider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasesFilterFragment : BaseFragment<FragmentBasesFilterBinding>() {

    private val viewModel: BaseFilterViewModel by viewModels()

    override fun getViewBinding(): FragmentBasesFilterBinding = FragmentBasesFilterBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            closeImg.setOnClickListener { findNavController().navigateUp() }
            baseFilterFromTextview.text = baseFilterSlider.values[0].toInt().toString()
            baseFilterToTextview.text = baseFilterSlider.values[1].toInt().toString()

            baseFilterSlider.addOnChangeListener(RangeSlider.OnChangeListener { slider, _, _ ->
                baseFilterFromTextview.text = slider.values[0].toInt().toString()
                baseFilterToTextview.text = slider.values[1].toInt().toString()
            })

            universityLayout.setOnClickListener {
                val action = BasesFilterFragmentDirections.actionBasesFilterFragment2ToBasesFilterUniversitiesFragment()
                findNavController().safeNavigate(action, R.id.basesFilterFragment)
            }

            cityLayout.setOnClickListener {
                val action = BasesFilterFragmentDirections.actionBasesFilterFragmentToBaseFilterCityFragment()
                findNavController().safeNavigate(action, R.id.basesFilterFragment)
            }
        }
    }

    private fun setupObservers() {

    }
}