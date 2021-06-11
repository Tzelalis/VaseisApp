package com.vaseis.app.ui.dashboard.calculator.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.vaseis.app.ui.dashboard.calculator.group.CalculatorGroupFragment

class ExamTypeViewPagerAdapter(fragment: Fragment, private val list: List<CalculatorExamType>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        val fragment = CalculatorGroupFragment()
        fragment.arguments = Bundle().apply {
            /*val groupIdList = mutableListOf<String>()
            for(calculatorGroup in list[position].calculatorGroups)
                groupIdList.add(calculatorGroup.id)

            putStringArrayList(CalculatorGroupFragment.GROUP_KEY, ArrayList(groupIdList))*/
            putParcelableArrayList(CalculatorGroupFragment.GROUP_KEY, ArrayList(list[position].calculatorGroups))
        }
        return fragment
    }
}