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
import androidx.fragment.app.Fragment
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.DialogAddBinding


fun Fragment.addCallDialog(): Boolean {
    var result = false
    val dialogBinding = DialogAddBinding.inflate(layoutInflater)
    val dialog = AlertDialog.Builder(requireContext())
        .setView(dialogBinding.root)
        .create() // Dialog 실제 생성 부분

    // 모서리 둥글기
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


    dialogBinding.saveBtn.setOnClickListener {
        val newName = dialogBinding.name.text.toString()
        val newPhoneNumber = dialogBinding.phoneNumber.text.toString()
        val newEmail = dialogBinding.email.text.toString()
        val newEvent = dialogBinding.event.text.toString()
        val newImage = dialogBinding.phoneImageView.toString()

        //이미 저장되어 있는 번호
        val existsName = ContactDatabase.findContactByName(newName)

        if (existsName != null) {
            Toast.makeText(requireContext(), "이미 저장되어 있는 번호 입니다", Toast.LENGTH_LONG).show()
            return@setOnClickListener
        } else if (!isValidPhoneNumber(newPhoneNumber)) {
            Toast.makeText(requireContext(), "번호 저장 방식이 잘못되었습니다", Toast.LENGTH_LONG).show()
            return@setOnClickListener
        } else {
            val newContact = ContactData(
                name = newName,
                profileImage = newImage,
                phoneNumber = newPhoneNumber,
                address = newEvent,
                email = newEmail,
            )
            // 연락처를 저장
            ContactDatabase.addContactData(newContact)

            Toast.makeText(requireContext(), "연락처가 저장되었습니다", Toast.LENGTH_LONG).show()

            dialog.dismiss()
            result = true
        }

        dialogBinding.cancleBtn.setOnClickListener {
            Toast.makeText(requireContext(), "취소 되었습니다", Toast.LENGTH_LONG).show()
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
    return result
}

fun isValidPhoneNumber(phoneNumber: String): Boolean {
    // 대한민국 휴대폰 번호 형식 예시: 010-1234-5678
    return phoneNumber.matches("^01[016789]\\d{3,4}\\d{4}$".toRegex())
}

