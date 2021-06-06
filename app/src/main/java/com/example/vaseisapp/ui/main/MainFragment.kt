package com.example.vaseisapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentMainLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainLayoutBinding>() {
    override fun getViewBinding(): FragmentMainLayoutBinding = FragmentMainLayoutBinding.inflate(layoutInflater)

    private val mainNavController: NavController by lazy { Navigation.findNavController(binding.mainNavHost) }

    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        //(activity as? AppActivity)?.setCustomSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        //setupNavigation()
    }

    private fun setupObservers() {
        with(viewModel) {
            navigationUI.observe(viewLifecycleOwner, { action ->
                mainNavController.navigate(action)
            })

            navigateFromAppNavGraphUI.observe(viewLifecycleOwner, { action ->
                findNavController().safeNavigate(action, R.id.mainFragment)
            })
        }
    }

    private fun setupViews() {
        with(binding) {
            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                viewModel.navigateTo(bottomNavigation.selectedItemId, item.itemId)
                true
            }

            val statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android")
            val statusBarHeight = resources.getDimensionPixelSize(statusBarHeightId)
            root.setPadding(0, statusBarHeight , 0, 0)
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