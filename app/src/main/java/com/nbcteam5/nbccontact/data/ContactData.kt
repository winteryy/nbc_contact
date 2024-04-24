package com.nbcteam5.nbccontact.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ContactData (
    val id:Long= -1,
    val name:String,
    val profileImage:String,
    val phoneNumber:String,
    val address:String,
    val email:String,
    val isFavorite:Boolean = false
) : Parcelable