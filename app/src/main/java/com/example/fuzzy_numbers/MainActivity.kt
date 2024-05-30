package com.example.fuzzy_numbers


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topTabLayout = findViewById<TabLayout>(R.id.topTabLayout)
        val bottomTabLayout = findViewById<TabLayout>(R.id.bottomTabLayout)
        val viewPagerTop = findViewById<ViewPager2>(R.id.viewPagerTop)
        val viewPagerBottom = findViewById<ViewPager2>(R.id.viewPagerBottom)

        // Set up ViewPagers with Adapters
        viewPagerTop.adapter = ViewPagerAdapter(this)
        viewPagerBottom.adapter = BottomViewPagerAdapter(this)

        // Link the TabLayout and the ViewPager2 for top ViewPager
        TabLayoutMediator(topTabLayout, viewPagerTop) { tab, position ->
            tab.text = "Top Tab ${(position + 1)}"
        }.attach()

        // Link the TabLayout and the ViewPager2 for bottom ViewPager
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