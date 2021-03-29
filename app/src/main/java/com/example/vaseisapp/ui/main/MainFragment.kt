package com.example.vaseisapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentMainLayoutBinding


class MainFragment : BaseFragment<FragmentMainLayoutBinding>() {
    override fun getViewBinding(): FragmentMainLayoutBinding = FragmentMainLayoutBinding.inflate(layoutInflater)

    private val mainNavController: NavController by lazy { Navigation.findNavController(binding.mainNavHost) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        //(activity as? AppActivity)?.setCustomSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        //setupNavigation()
    }

    private fun setupViews() {
        with(binding) {
            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.accountItem -> {
                        if(bottomNavigation.selectedItemId != R.id.accountItem)
                            mainNavController.navigate(R.id.accountFragment)
                        true
                    }
                    R.id.basesItem -> {
                        if(bottomNavigation.selectedItemId != R.id.basesItem)
                            mainNavController.navigate(R.id.departmentFragment)
                        true
                    }
                    R.id.calculatorItem -> {
                        if(bottomNavigation.selectedItemId != R.id.calculatorItem)
                            mainNavController.navigate(R.id.fragmentCalculator)
                        true
                    }
                    R.id.topicsItem -> {
                        if(bottomNavigation.selectedItemId != R.id.topicsItem)
                            mainNavController.navigate(R.id.topicsFragment)
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
        }
    }


    /* override fun onResume() {
         super.onResume()
         mainNavController.addOnDestinationChangedListener(listener)
     }

     override fun onPause() {
         mainNavController.removeOnDestinationChangedListener(listener)
         super.onPause()
     }

     private val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
         when (destination.id) {
             R.id.departmentDetailsFragment -> {
                 binding.bottomNavigation.isVisible = false
                 binding.toolbar.isVisible = true
             }

             R.id.departmentFragment -> {
                 binding.bottomNavigation.isVisible = true
                 binding.toolbar.isVisible = false
             }
         }

         Log.v("MPIKA", "MPIKARE MOUNI")
     }*/

}