package com.nbcteam5.nbccontact.presentation.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nbcteam5.nbccontact.R
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.ActivityMainBinding
import com.nbcteam5.nbccontact.presentation.detail.ContactDetailFragment
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
                    profileImage = "android.resource://com.nbcteam5.nbccontact/" + R.drawable.image,
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

    private fun checkPhonePermission() {
        if (ContextCompat.checkSelfPermission(this, (Manifest.permission.POST_NOTIFICATIONS )) == PackageManager.PERMISSION_GRANTED) {
            addCallDialog(onSuccess = {
                plugIn()
            })
        } else {
            requestPhonePermission()
        }
    }

    // 2. Phone 권한 요청
    private fun requestPhonePermission() {
        // 다이얼로그 작성 부위
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), PHONE_PERMISSION_CODE)
    }

    // 3. Phone 권한 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PHONE_PERMISSION_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addCallDialog(onSuccess = {
                        plugIn()
                    })
                } else {
                        // 권한 요청 거부했을 경우의 logic 처리
                        // ex) permission 재요청 or 무시 등등
                    Toast.makeText(this, "현재 거부되어 있습니다", Toast.LENGTH_LONG).show()
                }
            }
            CALL_PERMISSION_CODE -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                    request.launch(intent)
                } else {
                    Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViews() {
        binding.floatButton.setOnClickListener {
            // 권한 부여
            checkPhonePermission()

        }
    }

    private fun mainTrans() {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_container, MainFragment(), MAIN_FRAGMENT)
        trans.commit()
    }

    fun detailFromState(contactData: ContactData) {
        val bundle = Bundle()
        val detailFragment = ContactDetailFragment()
        bundle.putParcelable(DETAIL_FROM_STATE, contactData)
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

    private fun bindViews() = with(binding) {
        callButton.setOnClickListener {
            val status = ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CONTACTS)
            if(status == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                request.launch(intent)
            } else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf<String>(Manifest.permission.READ_CONTACTS), CALL_PERMISSION_CODE)
            }
        }
    }

    companion object {
        const val DETAIL_FROM_STATE = "detailfromstate"

        const val MAIN_FRAGMENT = "mainfragment"

        const val PHONE_PERMISSION_CODE = 999
        const val CALL_PERMISSION_CODE = 100
    }
}