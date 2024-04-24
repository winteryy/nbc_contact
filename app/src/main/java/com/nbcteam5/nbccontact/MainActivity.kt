package com.nbcteam5.nbccontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.nbcteam5.nbccontact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    private lateinit var viewPagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        val list = listOf(ContactListFragment(), ContactDetailFragment())
        viewPagerAdapter = ViewPagerAdapter(list, this)

        with(binding) {
            viewPager.adapter = viewPagerAdapter

            val title = listOf(R.drawable.baseline_call_24, R.drawable.baseline_contact_phone_24)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setIcon(title[position])

            }.attach()

            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if(tabLayout.selectedTabPosition == 1) {
                        tabLayout.isVisible = tabLayout.selectedTabPosition != 1
                    } else {
                        tabLayout.isVisible = true
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    if(tabLayout.selectedTabPosition == 1) {
                        tabLayout.isVisible = tabLayout.selectedTabPosition != 1
                    } else {
                        tabLayout.isVisible = true
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    if(tabLayout.selectedTabPosition == 1) {
                        tabLayout.isVisible = tabLayout.selectedTabPosition != 1
                    } else {
                        tabLayout.isVisible = true
                    }
                }
            })
        }

        binding.floatButton.setOnClickListener {
            //dialog 실행
        }
    }
}