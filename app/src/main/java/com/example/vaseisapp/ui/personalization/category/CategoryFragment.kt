package com.example.vaseisapp.ui.personalization.category

import android.os.Bundle
import android.view.View
import com.example.vaseisapp.base.BaseFragment
import com.example.vaseisapp.databinding.FragmentPersonalizationCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentPersonalizationCategoryBinding>() {

    override fun getViewBinding(): FragmentPersonalizationCategoryBinding = FragmentPersonalizationCategoryBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews()    {
        with(binding){
            val dummyList = listOf(Category("ΓΕΛ 90%", "Πρόκειτε για την πιο δημοφιλή κατηγορία", false), Category("ΕΠΑΛ", "Πρόκειτε για την πιο δημοφιλή κατηγορία", false), Category("ΕΣΠΕΡΙΝΑ", "Πρόκειτε για την πιο δημοφιλή κατηγορία", false))

            val listener = object : CategoryListAdapter.CategoryClickListener {
                override fun onClickListener(position: Int) {

                }

            }

            val adapter = CategoryListAdapter(listener)
            adapter.submitList(dummyList)
            categoryRecyclerView.adapter = adapter
        }
    }
}