package com.nbcteam5.nbccontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.databinding.ActivityMainBinding
import com.nbcteam5.nbccontact.util.addCallDialog

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mainTrans()
        initViews()
    }

    private fun initViews() {
        binding.floatButton.setOnClickListener {
            addCallDialog(onSuccess = {
                plugIn()
            })
        }
    }

    private fun mainTrans() {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_container, MainFragment(), MAIN_FRAGMENT)
        trans.commit()
    }

    fun detailFromList(contactData: ContactData) {
        val bundle = Bundle()
        val detailFragment = ContactDetailFragment()
        bundle.putParcelable(DETAIL_FROM_LIST, contactData)
        detailFragment.arguments = bundle

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_container, detailFragment)
        trans.setReorderingAllowed(true)
        trans.addToBackStack(null)
        trans.commit()
    }

    fun listFromDetail(isUpdated : Boolean) {
        supportFragmentManager.popBackStack()
        if(isUpdated) {
            plugIn()
        }
    }

    private fun plugIn() {
        (supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT) as MainFragment).reQuire()
    }

    companion object {
        const val DETAIL_FROM_LIST = "detailfromlist"
        const val LIST_FROM_DETAIL = "listfromdetail"

        const val MAIN_FRAGMENT = "mainfragment"
    }
}