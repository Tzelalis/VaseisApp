package com.example.vaseisapp.ui.personalization.theme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentThemeBinding
import com.example.vaseisapp.domain.prefs.Theme
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

    private fun setupViews()    {
        with(binding)   {
            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            themeUI.observe(viewLifecycleOwner, { theme ->
                when(theme){
                    Theme.SYSTEM_DEFAULT -> binding.defaultRadioButton.isChecked = true
                    Theme.LIGHT -> binding.lightRadioButton.isChecked = true
                    Theme.DARK -> binding.darkRadioButton.isChecked = true
                }
            })

            loadPrefTheme()
        }
    }
}