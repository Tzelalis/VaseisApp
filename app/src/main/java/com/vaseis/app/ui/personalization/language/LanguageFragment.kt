package com.vaseis.app.ui.personalization.language

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentLanguageBinding
import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.utils.setTopMarginForStatusBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>() {

    private val viewModel: LanguageViewModel by viewModels()

    override fun getViewBinding(): FragmentLanguageBinding = FragmentLanguageBinding.inflate(layoutInflater)

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
                    saveLanguage()

                    isEnabled = false
                    activity?.onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setupViews() {
        with(binding) {
            root.setTopMarginForStatusBar()

            backButtonLayout.backButtonImageView.setOnClickListener { activity?.onBackPressed() }

            proceedButton.setOnClickListener {
                saveLanguage()
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            currentLangUI.observe(viewLifecycleOwner, { lang ->
                when (lang) {
                    Language.SYSTEM_DEFAULT -> binding.defaultRadioButton.isChecked = true
                    Language.GREEK -> binding.greekRadioButton.isChecked = true
                    Language.ENGLISH -> binding.englishRadioButton.isChecked = true
                }
            })

            savedLangUI.observe(viewLifecycleOwner, { lang ->
                changeLanguage(lang)
            })

            loadLanguage()
        }
    }

    private fun changeLanguage(lang: Language) {
        var locale = Locale(lang.code)

        if (lang == Language.SYSTEM_DEFAULT)
            locale = Locale.getDefault()

        Locale.setDefault(locale)
        activity?.resources?.configuration?.setLocale(locale)
        activity?.resources?.updateConfiguration(activity?.resources?.configuration, activity?.resources?.displayMetrics)
    }

    private fun saveLanguage() {
        with(binding) {
            when (languageRadioGroup.checkedRadioButtonId) {
                defaultRadioButton.id -> viewModel.saveLanguage(Language.SYSTEM_DEFAULT)
                englishRadioButton.id -> viewModel.saveLanguage(Language.ENGLISH)
                greekRadioButton.id -> viewModel.saveLanguage(Language.GREEK)
            }
        }
    }
}