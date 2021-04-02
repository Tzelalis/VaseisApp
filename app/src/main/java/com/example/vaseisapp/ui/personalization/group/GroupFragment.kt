package com.example.vaseisapp.ui.personalization.group

import android.os.Bundle
import android.view.View
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentGroupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupFragment : BaseFragment<FragmentGroupBinding>() {
    override fun getViewBinding(): FragmentGroupBinding = FragmentGroupBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}