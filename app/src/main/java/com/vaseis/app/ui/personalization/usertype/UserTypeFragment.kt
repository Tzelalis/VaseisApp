package com.vaseis.app.ui.personalization.usertype

import android.os.Bundle
import android.view.View
import com.vaseis.app.base.BaseFragment
import com.vaseis.app.databinding.FragmentUserTypeBinding
import com.vaseis.app.utils.setTopMarginForStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserTypeFragment : BaseFragment<FragmentUserTypeBinding>(){
    override fun getViewBinding(): FragmentUserTypeBinding = FragmentUserTypeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews()    {
        with(binding)   {
            root.setTopMarginForStatusBar()

            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

}