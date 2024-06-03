package com.example.fuzzy_numbers.top

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TopViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0, 1 -> TableEditFragment.newInstance(position)
            2 -> TableReadFragment()
            else -> PlotResultFragment()
        }
    }
}
