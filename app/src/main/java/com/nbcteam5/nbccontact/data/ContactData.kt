package com.nbcteam5.nbccontact.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ContactData (
    val id:Long,
    val name:String,
    val profileImage:String,
    val phoneNumber:String,
    val address:String,
    val email:String,
    val isFavorite:Boolean
) : Parcelable