package com.example.vaseisapp.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentMainLayoutBinding
import com.example.vaseisapp.ui.AppActivity
import kotlinx.android.synthetic.main.fragment_main_layout.*


class MainFragment : BaseFragment<FragmentMainLayoutBinding>() {
    override fun getViewBinding(): FragmentMainLayoutBinding = FragmentMainLayoutBinding.inflate(layoutInflater)

    private val mainNavController: NavController by lazy { Navigation.findNavController(binding.mainNavHost) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //(activity as? AppActivity)?.setCustomSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        //setupNavigation()
    }

    private fun setupViews() {

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