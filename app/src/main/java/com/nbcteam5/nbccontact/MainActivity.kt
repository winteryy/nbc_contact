package com.nbcteam5.nbccontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        val list = listOf(,)
        viewPagerAdapter = ViewPagerAdapter(list, this)

        with(binding) {
            viewPager.adapter = viewPagerAdapter

            val title = listOf(R.drawable.baseline_call_24, R.drawable.baseline_contact_phone_24)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setIcon(title[position])

            }.attach()
            tabLayout.getTabAt(0)!!.view.visibility = View.VISIBLE
            tabLayout.getTabAt(1)!!.view.visibility = View.GONE
        }

        binding.floatButton.setOnClickListener {
            //dialog 실행
        }
    }
}