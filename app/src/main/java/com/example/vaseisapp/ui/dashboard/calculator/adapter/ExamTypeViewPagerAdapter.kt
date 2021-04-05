package com.example.vaseisapp.ui.dashboard.calculator.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vaseisapp.domain.calculation.entities.ExamType
import com.example.vaseisapp.ui.dashboard.calculator.group.CalculatorGroupFragment

class ExamTypeViewPagerAdapter(fragment: Fragment, private val list: List<ExamType>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        val fragment = CalculatorGroupFragment()
        fragment.arguments = Bundle().apply {
            putParcelableArrayList(CalculatorGroupFragment.GROUP_KEY, ArrayList(list[position].groups))
        }
        return fragment
    }
}