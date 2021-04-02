package com.example.vaseisapp.ui

import android.app.Activity
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.ActivityMainLayoutBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        //window.setBackgroundDrawable(null)

        val binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        _binding = binding

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController

        setContentView(binding.root)

        //makeStatusBarTransparent()


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


    fun setCustomSupportActionBar(toolbar: MaterialToolbar){
        setSupportActionBar(toolbar)
    }

    fun Activity.makeStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    fun View.setMarginTop(marginTop: Int) {
        val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        menuLayoutParams.setMargins(0, marginTop, 0, 0)
        this.layoutParams = menuLayoutParams
    }



}