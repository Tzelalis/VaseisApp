package com.example.vaseisapp.ui.personalization.examtype

import android.os.Bundle
import android.view.View
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentPersonalizationCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExamTypeFragment : BaseFragment<FragmentPersonalizationCategoryBinding>() {

    override fun getViewBinding(): FragmentPersonalizationCategoryBinding = FragmentPersonalizationCategoryBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews()    {
        with(binding){
            val dummyList = listOf(ExamType("ΓΕΛ 90%", "Πρόκειτε για την πιο δημοφιλή κατηγορία", false), ExamType("ΕΠΑΛ", "Πρόκειτε για την πιο δημοφιλή κατηγορία", false), ExamType("ΕΣΠΕΡΙΝΑ", "Πρόκειτε για την πιο δημοφιλή κατηγορία", false))

            val listener = object : ExamTypeListAdapter.CategoryClickListener {
                override fun onClickListener(position: Int) {

                }

            }

            val adapter = ExamTypeListAdapter(listener)
            adapter.submitList(dummyList)
            categoryRecyclerView.adapter = adapter
        }
    }
}