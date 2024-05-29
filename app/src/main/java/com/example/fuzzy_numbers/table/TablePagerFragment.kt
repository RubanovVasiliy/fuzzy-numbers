package com.example.fuzzy_numbers.table

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fuzzy_numbers.PagerAdapter
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.databinding.FragmentTablePagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class TablePagerFragment : Fragment() {

    private lateinit var binding: FragmentTablePagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment1: TableFragment = TableFragment.newInstance("Fragment 1")
        val fragment2: TableFragment = TableFragment.newInstance("Fragment 2")
        val fragment3: TableFragment = TableFragment.newInstance("Fragment 3")
        val fragmentList: List<Fragment> = arrayListOf(fragment1, fragment2, fragment3)

        val adapter = PagerAdapter(requireActivity(), fragmentList)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Число ${(position + 65).toChar()}"
        }.attach()
    }
}