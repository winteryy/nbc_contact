package com.nbcteam5.nbccontact.util



import android.content.Context
import com.nbcteam5.nbccontact.data.ContactData

object UserInformationManager {
    const val USER_INFORMATION = "userInformation"
    const val NAME = "name"
    const val PHONE_NUMBER = "phoneNumber"
    const val EMAIL = "email"
    const val EVENT = "event"

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // 대한민국 휴대폰 번호 형식 예시: 010-1234-5678
        return phoneNumber.matches("^01[016789]-\\d{3,4}-\\d{4}$".toRegex())
    }
}
