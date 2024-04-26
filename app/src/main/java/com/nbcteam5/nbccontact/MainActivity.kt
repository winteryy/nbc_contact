package com.nbcteam5.nbccontact

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.databinding.ActivityMainBinding
import com.nbcteam5.nbccontact.util.addCallDialog

const val PHONE_PERMISSION_CODE = 999
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mainTrans()
        initViews()

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

    companion object {
        const val DETAIL_FROM_LIST = "detailfromlist"
        const val LIST_FROM_DETAIL = "listfromdetail"

        const val MAIN_FRAGMENT = "mainfragment"
    }
}