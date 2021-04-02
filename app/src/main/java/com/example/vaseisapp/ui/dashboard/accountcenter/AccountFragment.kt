package com.example.vaseisapp.ui.dashboard.accountcenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentAccountBinding
import com.example.vaseisapp.ui.dashboard.accountcenter.adapters.PropertyAdapter
import com.example.vaseisapp.ui.dashboard.accountcenter.adapters.PropertyImageAdapter
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertyFragment
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertyItem
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
             val account = listOf(
                PropertyItem(null, resources.getString(R.string.account_account_title), ""),
                PropertyItem(PropertyFragment.CATEGORY, resources.getString(R.string.account_category_name), "ΓΕΛ 90%"),
                PropertyItem(PropertyFragment.USER_TYPE, resources.getString(R.string.account_user_type), "Student"),
            )

             val system = listOf(
                PropertyItem(null, resources.getString(R.string.account_system), ""),
                PropertyItem(PropertyFragment.THEME, resources.getString(R.string.account_theme), "Light"),
                PropertyItem(PropertyFragment.LANGUAGE, resources.getString(R.string.account_language), "English"),
            )

             val feedback = listOf(
                PropertyItem(null, resources.getString(R.string.account_feedback), ""),
                PropertyItem(PropertyFragment.RATE_US, resources.getString(R.string.account_rate), ""),
                PropertyItem(PropertyFragment.SHARE, resources.getString(R.string.account_share), ""),
                PropertyItem(PropertyFragment.BUG, resources.getString(R.string.account_bug), ""),
            )

             val credits = listOf(
                PropertyItem(null, resources.getString(R.string.account_credits), ""),
                PropertyItem(null, resources.getString(R.string.account_about_app), ""),
                PropertyItem(null, resources.getString(R.string.account_about_api), ""),
                PropertyItem(null, resources.getString(R.string.account_special_thanks), ""),
            )

            setupRecyclerView(listOf(account, system, feedback, credits), R.drawable.img_logo)
        }
    }

    private fun setupRecyclerView(listOfProperties: List<List<PropertyItem>>, imgId: Int) {
        val concatAdapter = ConcatAdapter()

        val listener = object : PropertyAdapter.PropertyListener {
            override fun onClickListener(property: PropertyItem) {
                when (property.id) {
                    PropertyFragment.SHARE -> shareApp()
                    PropertyFragment.BUG -> sendBug()
                    PropertyFragment.LANGUAGE -> changeLanguage()
                    else -> {
                    }
                }
            }
        }

        for (properties in listOfProperties) {
            val adapter = PropertyAdapter(listener)
            adapter.submitList(properties)
            concatAdapter.addAdapter(adapter)
        }

        val imageAdapter = PropertyImageAdapter()
        imageAdapter.submitList(listOf(imgId))
        concatAdapter.addAdapter(imageAdapter)

        binding.accountRecyclerView.adapter = concatAdapter
    }

    private fun shareApp() {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "Η καλύτερη εφαρμογή για τις ΠΑΝΕΛΛΗΝΙΕΣ!\n\nhttps://play.google.com/store/apps/details?id=com.SuncityDevs.stayhome"
            )
            //putExtra(Intent.EXTRA_TITLE, "Introducing content previews")
            //data = Uri.parse("https://vaseis.iee.ihu.gr/img/logo.png")
            //flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)
        startActivity(share)
    }

    private fun sendBug() {
        val intent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("examples@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Vaseis App, Bug Report")
        }, null)
        startActivity(intent)
    }

    fun changeLanguage() {
        val locale = Locale("el")
        Locale.setDefault(locale)
        activity?.resources?.configuration?.setLocale(locale)
        activity?.resources?.updateConfiguration(activity?.resources?.configuration, activity?.resources?.displayMetrics)
    }
}