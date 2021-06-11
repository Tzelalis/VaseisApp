package com.vaseis.app.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentSplashLayoutBinding
import com.vaseis.app.domain.prefs.Language
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashLayoutBinding>() {

    private val viewModel : SplashViewModel by viewModels()

    override fun getViewBinding(): FragmentSplashLayoutBinding  = FragmentSplashLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers(){
        with(viewModel) {
            showMainUi.observe(viewLifecycleOwner, {
                //val action = SplashFragmentDirections.actionSplashFragmentToPersonalizationFragment()    //testing
                val action = SplashFragmentDirections.toMainFragment()
                findNavController().navigate(action)
            })

            langPref.observe(viewLifecycleOwner, { lang ->
                changeLanguage(lang)
            })

            loadData()
        }
    }

    private fun changeLanguage(lang: Language) {
        var locale = Locale(lang.code)

        if(lang == Language.SYSTEM_DEFAULT)
            locale = Locale.getDefault()

        Locale.setDefault(locale)
        resources?.configuration?.setLocale(locale)
        resources?.updateConfiguration(resources.configuration, resources.displayMetrics)
    }
}