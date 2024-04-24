package com.nbcteam5.nbccontact

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nbcteam5.nbccontact.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate( layoutInflater )

        initViews()
        return binding.root
    }

    private fun initViews() {
        val list = listOf(ContactListFragment(), MyPageFragment())
        viewPagerAdapter = ViewPagerAdapter(list, requireActivity())

        with(binding) {
            viewPager.adapter = viewPagerAdapter

            val title = listOf(R.drawable.baseline_call_24, R.drawable.baseline_contact_phone_24)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setIcon(title[position])

            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
    }


}