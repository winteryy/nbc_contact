package com.nbcteam5.nbccontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nbcteam5.nbccontact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        setFrom()
    }

    private fun initViews() {

        binding.floatButton.setOnClickListener {
            //dialog 실행
        }
    }

    private fun setFrom() {
        val move = supportFragmentManager.beginTransaction()
        move.replace(R.id.fragment_layout, MainFragment())
        move.commit()
    }
    /*fun goFrom() { //리스트 페이지 이동
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_layout, FragmentC())
        trans.commit()
    }*/
}