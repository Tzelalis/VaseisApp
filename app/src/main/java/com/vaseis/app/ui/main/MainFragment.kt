package com.vaseis.app.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.vaseis.app.R
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentMainLayoutBinding
import com.vaseis.app.utils.setTopMarginForStatusBar
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

            root.setTopMarginForStatusBar()
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