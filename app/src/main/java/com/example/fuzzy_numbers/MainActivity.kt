package com.example.fuzzy_numbers

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.fuzzy_numbers.bottom.BottomViewPagerAdapter
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.top.TopViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FuzzyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topTabLayout = findViewById<TabLayout>(R.id.topTabLayout)
        val bottomTabLayout = findViewById<TabLayout>(R.id.bottomTabLayout)
        val viewPagerTop = findViewById<ViewPager2>(R.id.viewPagerTop)
        val viewPagerBottom = findViewById<ViewPager2>(R.id.viewPagerBottom)

        viewPagerTop.adapter = TopViewPagerAdapter(this)
        viewPagerBottom.adapter = BottomViewPagerAdapter(this)

        TabLayoutMediator(topTabLayout, viewPagerTop) { tab, position ->
            tab.text = when (position) {
                0 -> "Table 1"
                1 -> "Table 2"
                2 -> "Table 3"
                else -> ""
            }
        }.attach()

        TabLayoutMediator(bottomTabLayout, viewPagerBottom) { tab, position ->
            tab.text = when (position) {
                0 -> "Операции"
                1 -> "Сравнение"
                2 -> "График"
                else -> ""
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Handle settings action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
