package com.example.vaseisapp.ui.personalization.theme

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentThemeBinding
import com.example.vaseisapp.domain.prefs.Theme
import com.example.vaseisapp.utils.ThemeHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeFragment : BaseFragment<FragmentThemeBinding>() {

    private val viewModel: ThemeViewModel by viewModels()

    override fun getViewBinding(): FragmentThemeBinding = FragmentThemeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                saveTheme()

                isEnabled = false
                activity?.onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    private fun setupViews() {
        with(binding) {
            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }

            proceedButton.setOnClickListener {
                saveTheme()
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            themeUI.observe(viewLifecycleOwner, { theme ->
                when (theme) {
                    Theme.SYSTEM_DEFAULT -> binding.defaultRadioButton.isChecked = true
                    Theme.LIGHT -> binding.lightRadioButton.isChecked = true
                    Theme.DARK -> binding.darkRadioButton.isChecked = true
                }
            })

            savedUI.observe(viewLifecycleOwner, { theme ->
                ThemeHelper.apply(theme)
            })

            loadPrefTheme()
        }
    }

    private fun saveTheme() {
        with(binding) {
            when (binding.themeRadioGroup.checkedRadioButtonId) {
                defaultRadioButton.id -> viewModel.saveTheme(Theme.SYSTEM_DEFAULT)
                lightRadioButton.id -> viewModel.saveTheme(Theme.LIGHT)
                darkRadioButton.id -> viewModel.saveTheme(Theme.DARK)
            }
        }
    }


}