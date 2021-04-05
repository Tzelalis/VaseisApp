package com.example.vaseisapp.ui.personalization.language

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentLanguageBinding
import com.example.vaseisapp.domain.prefs.Language
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

    private fun setupViews() {
        with(binding) {
            backButtonLayout.backButtonImageView.setOnClickListener { activity?.onBackPressed() }

            proceedButton.setOnClickListener {
                when (languageRadioGroup.checkedRadioButtonId) {
                    defaultRadioButton.id -> viewModel.saveLanguage(Language.SYSTEM_DEFAULT)
                    englishRadioButton.id -> viewModel.saveLanguage(Language.ENGLISH)
                    greekRadioButton.id -> viewModel.saveLanguage(Language.GREEK)
                }
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
                activity?.onBackPressed()
            })

            loadLanguage()
        }
    }

    private fun changeLanguage(lang: Language) {
        val locale = Locale(lang.code)
        Locale.setDefault(locale)
        activity?.resources?.configuration?.setLocale(locale)
        activity?.resources?.updateConfiguration(activity?.resources?.configuration, activity?.resources?.displayMetrics)
    }
}