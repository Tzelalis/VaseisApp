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


@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            val dummy = listOf(
                PropertyItem(null, "My Account", ""),
                PropertyItem(PropertyFragment.CATEGORY, "Category", "ΓΕΛ 90%"),
                PropertyItem(PropertyFragment.USER_TYPE, "User Type", "Student"),
            )
            val dummy1 = listOf(
                PropertyItem(null, "System", ""),
                PropertyItem(PropertyFragment.THEME, "Theme", "Dark"),
                PropertyItem(PropertyFragment.LANGUAGE, "Language", "English"),
            )

            val dummy2 = listOf(
                PropertyItem(null, "Feedback", ""),
                PropertyItem(PropertyFragment.RATE_US, "Rate us", ""),
                PropertyItem(PropertyFragment.SHARE, "Share with my friends", ""),
                PropertyItem(PropertyFragment.BUG, "I spotted a Bug", ""),
            )

            val dummy3 = listOf(
                PropertyItem(null, "Credits", ""),
                PropertyItem(null, "About the App", ""),
                PropertyItem(null, "About the API", ""),
                PropertyItem(null, "Special Thanks", ""),
            )

            setupRecyclerView(listOf(dummy, dummy1, dummy2, dummy3), R.drawable.img_logo)
        }
    }

    private fun setupRecyclerView(listOfProperties: List<List<PropertyItem>>, imgId: Int) {
        val concatAdapter = ConcatAdapter()

        val listener = object : PropertyAdapter.PropertyListener {
            override fun onClickListener(property: PropertyItem) {
                when (property.id) {
                    PropertyFragment.SHARE -> shareApp()
                    PropertyFragment.BUG -> sendBug()
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
}