package com.nbcteam5.nbccontact.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.Window

import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.nbcteam5.nbccontact.databinding.DialogAddBinding

@RequiresApi(Build.VERSION_CODES.S)
fun Activity.addCallDialog() {
    val dialogBinding = DialogAddBinding.inflate(layoutInflater)
    val dialog = AlertDialog.Builder(this)
        .setView(dialogBinding.root)
        .create() // Dialog 실제 생성 부분

    val sharedPreferences = getSharedPreferences(UserInformationManager.USER_INFORMATION, Context.MODE_PRIVATE)
    // 모서리 둥글기
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


    dialogBinding.saveBtn.setOnClickListener {
        val newName = dialogBinding.name.text.toString()
        val newPhoneNumber = dialogBinding.phoneNumber.text.toString()
        val newEmail = dialogBinding.email.text.toString()
        val newEvent = dialogBinding.event.text.toString()
        // 이미 저장되어 있는 이름과 번호
        val existingName = sharedPreferences.getString(UserInformationManager.NAME, null)
        val existingPhoneNumber = sharedPreferences.getString(UserInformationManager.PHONE_NUMBER, null)

        if (newName == existingName) {
            Toast.makeText(this, "이미 저장된 이름입니다.", Toast.LENGTH_LONG).show()
        } else if (newPhoneNumber == existingPhoneNumber) {
            Toast.makeText(this, "이미 저장된 번호입니다.", Toast.LENGTH_LONG).show()
        } else {
            with(sharedPreferences.edit()) {
                putString(UserInformationManager.NAME, newName)
                putString(UserInformationManager.PHONE_NUMBER, newPhoneNumber)
                putString(UserInformationManager.EMAIL, newEmail)
                putString(UserInformationManager.EVENT, newEvent)
                apply()
            }
            Toast.makeText(this, "저장이 되었습니다", Toast.LENGTH_LONG).show()
            dialog.dismiss() // 저장 후 대화상자 닫기
        }
    }

    dialogBinding.cancleBtn.setOnClickListener {
        Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_LONG).show()
        dialog.dismiss() // 취소 버튼 클릭 후 대화상자 닫기
    }

    dialog.show()

    // 다이얼로그의 크기 조정
    val window = dialog.window
    if (window != null) {
        val displayMetrics = resources.displayMetrics.density
        val dpWidth = 300
        val dpHeight = 600
        val width = (displayMetrics * dpWidth).toInt()
        val height = (displayMetrics * dpHeight).toInt()

        window.setLayout(width, height)
    }
}