package com.nbcteam5.nbccontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.nbcteam5.nbccontact.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var viewPagerAdapter : ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate( layoutInflater )
        initViews()

        return binding.root
    }

    private fun initViews() {
        val list = listOf(, )
        viewPagerAdapter = ViewPagerAdapter(list, requireActivity())

        with(binding) {
            viewPager.adapter = viewPagerAdapter

            val title = listOf(R.drawable.baseline_call_24, R.drawable.baseline_contact_phone_24)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setIcon(title[position])

                //((tabLayout.getTabAt(1)?.view))?.isVisible == false
            }.attach()
        }
    }
}