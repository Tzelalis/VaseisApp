package com.vaseis.app.ui.dashboard.calculator.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vaseis.app.ui.dashboard.calculator.group.CalculatorGroupFragment

class ExamTypeViewPagerAdapter(fragment: Fragment, private val list: List<Unit>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        val fragment = CalculatorGroupFragment()
        fragment.arguments = Bundle().apply {
            putInt(CalculatorGroupFragment.GROUP_KEY, position)
        }
        return fragment
    }
}