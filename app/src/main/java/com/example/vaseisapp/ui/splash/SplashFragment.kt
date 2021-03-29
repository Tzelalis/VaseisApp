package com.example.vaseisapp.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentSplashLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

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

            loadStartData()
        }
    }
}