@file:Suppress("UNREACHABLE_CODE")

package com.nbcteam5.nbccontact.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.SystemClock
import android.util.Patterns
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.chip.Chip
import com.nbcteam5.nbccontact.R
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.DialogAddBinding
import com.nbcteam5.nbccontact.util.Constant.Companion.ALARM_10TIMER
import com.nbcteam5.nbccontact.util.Constant.Companion.ALARM_30TIMER
import com.nbcteam5.nbccontact.util.Constant.Companion.ALARM_5TIMER
import com.nbcteam5.nbccontact.util.Constant.Companion.NOTIFICATION_ID


@SuppressLint("ScheduleExactAlarm")
@Suppress("DEPRECATION")
fun Activity.addCallDialog(
    onSuccess: () -> Unit
) {
    val dialogBinding = DialogAddBinding.inflate(layoutInflater)
    val dialog = AlertDialog.Builder(this)
        .setView(dialogBinding.root)
        .create() // Dialog 실제 생성 부분

    // 모서리 둥글기
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    // chip 그룹
    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
    val intent = Intent(this, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        this, NOTIFICATION_ID, intent,
        PendingIntent.FLAG_IMMUTABLE
    )
    val chipGroup = dialogBinding.chipGroup
    chipGroup.setOnCheckedChangeListener { group, checkedId ->
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as Chip
            if (chip.id == checkedId) {
                // 선택된 Chip의 배경색을 변경
                chip.chipBackgroundColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this,
                        R.color.theme_green
                    )
                )
            } else {
                // 다른 Chip들은 원래의 색상으로 되돌아 간다
                chip.chipBackgroundColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this,
                        R.color.theme_light_green
                    )
                )
            }
        }
    }

    dialogBinding.saveBtn.setOnClickListener {
        val newName = dialogBinding.name.text.toString()
        val newPhoneNumber = dialogBinding.phoneNumber.text.toString()
        val newEmail = dialogBinding.email.text.toString()
        val newAdress = dialogBinding.adress.text.toString()
        val newImage = dialogBinding.phoneImageView.toString()

        //이미 저장되어 있는 번호
        val existPhoneNumber = ContactDatabase.findContactByName(newPhoneNumber, newName)

        if (existPhoneNumber != null) {
            Toast.makeText(this, "이미 저장되어 있는 번호 입니다", Toast.LENGTH_LONG).show()
            return@setOnClickListener
        } else if (!isValidPhoneNumber(newPhoneNumber)) {
            Toast.makeText(this, "번호 저장 방식이 잘못되었습니다", Toast.LENGTH_LONG).show()
            return@setOnClickListener
        } else if (!isValidEamil(newEmail)) {
            Toast.makeText(this, "이메일 작성방법이 잘못되었습니다.", Toast.LENGTH_LONG).show()
        } else {
            val selectedChipId = dialogBinding.chipGroup.checkedChipId
            if (selectedChipId != -1) {
                setNotification(selectedChipId)
            }
            val newContact = ContactData(
                name = newName,
                profileImage = newImage,
                phoneNumber = newPhoneNumber,
                address = newAdress,
                email = newEmail,
            )
            // 연락처를 저장
            ContactDatabase.addContactData(newContact)
            Toast.makeText(this, "연락처가 저장되었습니다", Toast.LENGTH_LONG).show()
            onSuccess.invoke()

            dialog.dismiss()
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
        val dpWidth = 400
        val dpHeight = 600
        val width = (displayMetrics * dpWidth).toInt()
        val height = (displayMetrics * dpHeight).toInt()

        window.setLayout(width, height)
    }
}

private fun isValidPhoneNumber(phoneNumber: String): Boolean {
    // 대한민국 휴대폰 번호 형식 예시: 010-1234-5678
    return phoneNumber.matches("^01[016789]\\d{3,4}\\d{4}$".toRegex())
}

private fun isValidEamil(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@SuppressLint("ScheduleExactAlarm")
fun Activity.setNotification(selectedChipId: Int) {
    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
    val intent = Intent(this, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    when (selectedChipId) {
        R.id.off -> {
            null
        }

        R.id.minute5 -> {
            val triggerTime = SystemClock.elapsedRealtime() + ALARM_5TIMER * 1000
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Toast.makeText(this, "$ALARM_5TIMER 분 후에 알림이 발생합니다.", Toast.LENGTH_LONG).show()
        }

        R.id.minute10 -> {
            val triggerTime = SystemClock.elapsedRealtime() + ALARM_10TIMER * 1000
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Toast.makeText(this, "$ALARM_10TIMER 분 후에 알림이 발생합니다.", Toast.LENGTH_LONG).show()
        }

        R.id.minute30 -> {
            val triggerTime = SystemClock.elapsedRealtime() + ALARM_30TIMER * 1000
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Toast.makeText(this, "$ALARM_30TIMER 분 후에 알림이 발생합니다.", Toast.LENGTH_LONG).show()
        }
    }
}



