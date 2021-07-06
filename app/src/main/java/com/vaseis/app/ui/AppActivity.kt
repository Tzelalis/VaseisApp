package com.vaseis.app.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.vaseis.app.R
import com.vaseis.app.databinding.ActivityMainLayoutBinding
import com.vaseis.app.ui.main.MainViewModel
import com.vaseis.app.utils.ErrorLiveData
import com.vaseis.app.utils.ThemeHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main_layout.view.*
import kotlinx.android.synthetic.main.fragment_main_layout.*
import java.util.*


@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    private var _binding: ActivityMainLayoutBinding? = null
    private val viewModel: AppViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var toast : Toast

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        _binding = binding

        setContentView(binding.root)

        toast = Toast(this)
        toast.duration = Toast.LENGTH_SHORT

        setupObservers()
        setupViews()
    }

    private fun setupViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController
    }


    private fun setupObservers() {
        ErrorLiveData.observe(this, { errorId ->
            toast.setText(getString(errorId))
            toast.show()
        })

        with(viewModel) {
            toolbarTitle.observe(this@AppActivity, {

            })

            themeUI.observe(this@AppActivity, { theme ->
                ThemeHelper.apply(theme)
            })

            loadTheme()
        }
    }
}