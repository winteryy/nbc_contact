package com.nbcteam5.nbccontact.data

import com.nbcteam5.nbccontact.R

object ContactDatabase {

    const val RES_URI = "android.resource://com.nbcteam5.nbccontact/"

    //data
    private val contact1 = ContactData(1,"서준",RES_URI+R.drawable.person1,"01011111111","경기도","boll1235@gmail.com",false)
    private val contact2 = ContactData(2,"지현", RES_URI+R.drawable.person2,"01011111112","경기도","boll@gmail.com", false)
    private val contact3 = ContactData(3,"민지", RES_URI+R.drawable.person3,"01011111110","서울","boll12@gmail.com",false)
    private val contact4 = ContactData(4,"준호", RES_URI+R.drawable.person4,"01011111117","경기도","boll0@gmail.com",false)
    private val contact5 = ContactData(5,"윤지", RES_URI+R.drawable.person5,"01011111112","인천","bol@gmail.com",false)
    private val contact6 = ContactData(6,"혜진", RES_URI+R.drawable.person6,"01011211112","부산","boll555@gmail.com",false)
    private val contact7 = ContactData(7,"승현", RES_URI+R.drawable.person7,"01011111712","제주도","crab@gmail.com",false)
    private val contact8 = ContactData(8,"수진", RES_URI+R.drawable.person8,"01011111100","경상도","bo@gmail.com",false)
    private val contact9 = ContactData(9,"재원", RES_URI+R.drawable.person9,"010111111612","경기도","boll999@gmail.com",false)
    private val contact10 = ContactData(10,"애리", RES_URI+R.drawable.person10,"010112311612","전라도","bl999@gmail.com",false)
    private val contact11 = ContactData(11,"현우", RES_URI+R.drawable.person11,"010231111612","경상북도","boll999@gmail.com",false)

    private var idNum = 12L

    private var _totalContactData: List<ContactData> = listOf(
        contact1,
        contact2,
        contact3,
        contact4,
        contact5,
        contact6,
        contact7,
        contact8,
        contact9,
        contact10,
        contact11
    )

    //저장된 데이터를 이름순으로 정렬하는 함수
    fun nameSorting() = totalContactData.sortedWith(compareBy({!it.isFavorite},{it.name}))

    private val totalContactData: List<ContactData> get() = _totalContactData

    fun getContactData(): List<ContactData> = totalContactData

    fun addContactData(newData: ContactData): Boolean {
        _totalContactData = _totalContactData.toMutableList().apply {
            add(newData.copy(id = idNum, isFavorite = false))
        }.toList()
        idNum++

        return true
    }

    fun updateContactData(newData: ContactData): Boolean {
        var flag = false
        _totalContactData = _totalContactData.map {
            if(it.id == newData.id) {
                flag = true
                newData
            }else {
                it
            }
        }
        return flag
    }

    fun deleteContactData(id: Long): Boolean {
        val itemInd = _totalContactData.binarySearchBy(id){
            it.id
        }

        return if(itemInd>=0) {
            _totalContactData = _totalContactData.filter {
                it.id != id
            }
            true
        } else {
            false
        }
    }

}