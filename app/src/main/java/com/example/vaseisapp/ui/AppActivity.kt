package com.example.vaseisapp.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ActivityMainLayoutBinding
import com.example.vaseisapp.domain.prefs.Language
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main_layout.view.*
import kotlinx.android.synthetic.main.fragment_main_layout.*
import java.util.*


@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    companion object {
        const val LOCALE_PREFS = "LOCAL_PREFS"
        const val GREEK_LANGUAGE = "GREEK_LANGUAGE"
        const val ENGLISH_LANGUAGE = "ENGLISH_LANGUAGE"
        const val DEFAULT_LANGUAGE = "DEFAULT_LANGUAGE"
    }

    private var _binding: ActivityMainLayoutBinding? = null
    private val binding = _binding
    private val viewModel: AppViewModel by viewModels()

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        _binding = binding

        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupViews()    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController
    }
    private fun setupObservers() {
        with(viewModel) {
            toolbarTitle.observe(this@AppActivity, {

            })
        }
    }
}