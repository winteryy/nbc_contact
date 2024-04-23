package com.nbcteam5.nbccontact.util



object UserInformation{
    const val USER_INFORMATION = "userInformation"
    const val NAME = "name"
    const val PHONE_NUMBER = "phoneNumber"
    const val EMAIL = "email"
    const val EVENT = "event"

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val regex = "^01[016789]\\d{3,4}\\d{4}$".toRegex()
        return phoneNumber.matches(regex)
    }
}
