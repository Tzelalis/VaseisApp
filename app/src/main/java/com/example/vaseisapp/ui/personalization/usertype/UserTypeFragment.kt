package com.example.vaseisapp.ui.personalization.usertype

import android.os.Bundle
import android.view.View
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentUserTypeBinding
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
            backButtonLayout.backButtonImageView.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

}