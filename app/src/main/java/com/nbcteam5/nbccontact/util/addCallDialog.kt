package com.nbcteam5.nbccontact.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nbcteam5.nbccontact.databinding.DialogAddBinding

fun Activity.addCallDialog() {
    AlertDialog.Builder(this).apply {
        val dialogBinding = DialogAddBinding.inflate(layoutInflater)
        setView(dialogBinding.root)
        setPositiveButton("확인") { _, _ ->
            with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
                putString(NAME , dialogBinding.name.text.toString())
                putString(PHONE_NUMEBER, dialogBinding.phoneNumber.text.toString())
                putString(EMAIL , dialogBinding.email.text.toString())
                putString(EVENT , dialogBinding.event.text.toString())
                apply()
            }
            Toast.makeText(this@addCallDialog, "전화번호부가 저장되었습니다", Toast.LENGTH_LONG).show()
        }
        setNegativeButton("취소", null)
    }.show()
}