package com.example.vaseisapp.ui.dashboard.accountcenter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentAccountBinding
import com.example.vaseisapp.ui.dashboard.accountcenter.adapters.PropertyAdapter
import com.example.vaseisapp.ui.dashboard.accountcenter.adapters.PropertyImageAdapter
import com.example.vaseisapp.ui.dashboard.accountcenter.adapters.PropertyVersionAdapter
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertiesFromPrefs
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertyFragment
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertyItem
import com.example.vaseisapp.ui.main.MainFragmentDirections
import com.example.vaseisapp.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: AccountViewModel by viewModels()

    private val accountAdapter: PropertyAdapter by lazy { PropertyAdapter(listener) }
    private val systemAdapter: PropertyAdapter by lazy { PropertyAdapter(listener) }
    private val feedbackAdapter: PropertyAdapter by lazy { PropertyAdapter(listener) }
    private val creditsAdapter: PropertyAdapter by lazy { PropertyAdapter(listener) }
    private val imageAdapter: PropertyImageAdapter by lazy { PropertyImageAdapter() }
    private val versionAdapter: PropertyVersionAdapter by lazy { PropertyVersionAdapter() }

    private val concatAdapter: ConcatAdapter by lazy { ConcatAdapter() }
    private val listener = object : PropertyAdapter.PropertyListener {
        override fun onClickListener(property: PropertyItem) {
            when (property.id) {
                PropertyFragment.SHARE -> shareApp()
                PropertyFragment.BUG -> sendBug()
                PropertyFragment.LANGUAGE -> mainViewModel.navigate(MainFragmentDirections.actionMainToLanguage())
                PropertyFragment.USER_TYPE -> mainViewModel.navigate(MainFragmentDirections.actionMainToUserType())
                PropertyFragment.EXAM_TYPE -> mainViewModel.navigate(MainFragmentDirections.actionMainToExamType())
                PropertyFragment.GROUP_TYPE -> mainViewModel.navigate(MainFragmentDirections.actionMainToGroup())
                PropertyFragment.RATE_US -> rateUs()
                PropertyFragment.THEME -> {

                    activity?.recreate()
                }
                else -> {
                }
            }
        }
    }

    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            concatAdapter.addAdapter(accountAdapter)
            concatAdapter.addAdapter(systemAdapter)
            concatAdapter.addAdapter(feedbackAdapter)
            concatAdapter.addAdapter(creditsAdapter)
            concatAdapter.addAdapter(versionAdapter)
            concatAdapter.addAdapter(imageAdapter)

            accountRecyclerView.adapter = concatAdapter
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            propertiesUI.observe(viewLifecycleOwner, { prefs ->
                setupRecyclerView(prefs, R.drawable.img_logo)
            })

            loadData()
        }
    }

    private fun setupRecyclerView(prefs: PropertiesFromPrefs, imgId: Int) {
        val accountList = listOf(
            PropertyItem(null, getString(R.string.account_account_title), ""),
            PropertyItem(PropertyFragment.USER_TYPE, getString(PropertyFragment.USER_TYPE.stringId), ""),
            PropertyItem(PropertyFragment.EXAM_TYPE, getString(PropertyFragment.EXAM_TYPE.stringId), prefs.examsType),
            PropertyItem(PropertyFragment.GROUP_TYPE, getString(PropertyFragment.GROUP_TYPE.stringId), prefs.groupType),
        )
        val systemList = listOf(
            PropertyItem(null, getString(R.string.account_system), ""),
            PropertyItem(PropertyFragment.THEME, getString(PropertyFragment.THEME.stringId), ""),
            PropertyItem(PropertyFragment.LANGUAGE, getString(PropertyFragment.LANGUAGE.stringId), getString(prefs.language)),
        )
        val feedbackList = listOf(
            PropertyItem(null, getString(R.string.account_feedback), ""),
            PropertyItem(PropertyFragment.RATE_US, getString(PropertyFragment.RATE_US.stringId), ""),
            PropertyItem(PropertyFragment.SHARE, getString(PropertyFragment.SHARE.stringId), ""),
            PropertyItem(PropertyFragment.BUG, getString(PropertyFragment.BUG.stringId), ""),
        )
        val creditsList = listOf(
            PropertyItem(null, getString(R.string.account_credits), ""),
            PropertyItem(null, getString(R.string.account_about_app), ""),
            PropertyItem(null, getString(R.string.account_about_api), ""),
            PropertyItem(null, getString(R.string.account_special_thanks), ""),
        )
        val versionList = listOf("Version ${context?.packageManager?.getPackageInfo(requireContext().packageName, 0)?.versionName}")

        accountAdapter.submitList(accountList)
        systemAdapter.submitList(systemList)
        feedbackAdapter.submitList(feedbackList)
        creditsAdapter.submitList(creditsList)
        versionAdapter.submitList(versionList)
        imageAdapter.submitList(listOf(imgId))
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

    private fun rateUs() {
        try {
            //todo change to real id
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity?.packageName)))
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.facebook.katana")))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + activity?.packageName)))
        }
    }


}