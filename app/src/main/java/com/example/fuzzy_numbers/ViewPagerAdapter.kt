package com.example.fuzzy_numbers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3 // or any number of tabs you want
    }

    override fun createFragment(position: Int): Fragment {
        return SampleFragment.newInstance(position)
    }
}
