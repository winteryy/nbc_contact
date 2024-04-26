package com.nbcteam5.nbccontact

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nbcteam5.nbccontact.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentMainBinding? = null
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var mainActivity: MainActivity
    private val list = listOf(ContactListFragment(), MyPageFragment())

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

        viewPagerAdapter = ViewPagerAdapter(list, requireActivity())
        binding.viewPager.adapter = viewPagerAdapter
        with(binding) {

            val title = listOf(R.drawable.baseline_call_24, R.drawable.baseline_contact_phone_24)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setIcon(title[position])

            }.attach()
        }

    }

    fun reQuire() {
        (list[0] as ContactListFragment)
    }


}