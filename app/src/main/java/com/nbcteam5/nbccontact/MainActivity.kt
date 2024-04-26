package com.nbcteam5.nbccontact

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.ActivityMainBinding
import com.nbcteam5.nbccontact.util.addCallDialog

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    private val request = registerForActivityResult( ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == RESULT_OK) {
            val cursor = contentResolver.query(
                it.data!!.data!!,
                arrayOf<String>(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                null
            )
            if(cursor!!.moveToFirst()) {
                val name = cursor.getString(0)
                val phone = cursor.getString(1)
                val combine = ContactData(
                    id = -1,
                    name = name,
                    profileImage = "android.resource://com.nbcteam5.nbccontact/" + R.drawable.person10,
                    phoneNumber = phone,
                    address = "서울시",
                    email = "weqwewq@naver.com"
                )
                ContactDatabase.addContactData(combine)
                plugIn()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mainTrans()
        initViews()
        bindViews()
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

    fun detailFromMyPage(contactData: ContactData) {
        val bundle = Bundle()
        val detailFragment = ContactDetailFragment()
        bundle.putParcelable(DETAIL_FROM_MY, contactData)
        detailFragment.arguments = bundle

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_container, detailFragment)
        trans.setReorderingAllowed(true)
        trans.addToBackStack(null)
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
        if(isUpdated) {
            plugIn()
        }
    }

    private fun plugIn() {
        (supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT) as MainFragment).reQuire()
    }

    @SuppressLint("Recycle")
    private fun bindViews() = with(binding) {
        callButton.setOnClickListener {
            val status = ContextCompat.checkSelfPermission(this@MainActivity, "android.permission.READ_CONTACTS")
            if(status == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                request.launch(intent)
            } else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf<String>("android.permission.READ_CONTACTS"), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            request.launch(intent)
        } else {
            Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val DETAIL_FROM_LIST = "detailfromlist"
        const val DETAIL_FROM_MY = "detailfrommy"
        const val LIST_FROM_DETAIL = "listfromdetail"

        const val MAIN_FRAGMENT = "mainfragment"
    }
}