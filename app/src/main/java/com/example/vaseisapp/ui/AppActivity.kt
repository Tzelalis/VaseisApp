package com.example.vaseisapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ActivityMainLayoutBinding
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main_layout.view.*
import kotlinx.android.synthetic.main.fragment_main_layout.*

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    private var _binding : ActivityMainLayoutBinding? = null
    private val binding = _binding
    private val viewModel : AppViewModel by viewModels()

    private lateinit var controller : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        _binding = binding

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController

        setContentView(binding.root)

     /*   setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration(controller.graph)
        binding.toolbar.setupWithNavController(controller, appBarConfiguration)*/

        setupObservers()
        //supportActionBar?.setShowHideAnimationEnabled(true)
    }

  /*  fun test(flag : Boolean)  {
        if(flag){
            supportActionBar?.hide()
            binding?.mainLayout?.animate()?.translationY(-binding.toolbar.height.toFloat())?.setInterpolator(AccelerateInterpolator(2f))?.start()
        } else{
            binding?.mainLayout?.animate()?.translationY(0f)?.setInterpolator(AccelerateInterpolator(2f))?.start();
            supportActionBar?.show()

        }

    }*/

    private fun setupObservers()    {
        with(viewModel) {
            toolbarTitle.observe(this@AppActivity, {

            })
        }
    }


    fun setCustomSupportActionBar(toolbar : MaterialToolbar){
        setSupportActionBar(toolbar)
    }




}