package com.example.fuzzy_numbers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottomViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OperationsFragment()
            1 -> ComparisonFragment()
            2 -> GraphFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}