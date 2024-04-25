package com.nbcteam5.nbccontact

import com.nbcteam5.nbccontact.data.ContactData

object ContactBookmarkManager {

    val contactBookmarkList: MutableList<ContactData> = mutableListOf<ContactData>()

    fun toggleFavoriteContact(contact: ContactData){
        val existingContactIndex = contactBookmarkList.indexOfFirst { it.id == contact.id }
        if(existingContactIndex != -1){

            val existingContact = contactBookmarkList[existingContactIndex]
            val updatedContact = existingContact.copy(isFavorite = !existingContact.isFavorite)
            contactBookmarkList[existingContactIndex] = updatedContact
        } else {

            contactBookmarkList.add(contact.copy(isFavorite = true))
        }
        contactBookmarkList.sortBy { it.name }
    }

    fun deleteContactFromBookmark(contact: ContactData) {
        val existingContactIndex = contactBookmarkList.indexOfFirst { it.id == contact.id }
        if(existingContactIndex != -1){
            contactBookmarkList.removeAt(existingContactIndex)
        }
    }
}