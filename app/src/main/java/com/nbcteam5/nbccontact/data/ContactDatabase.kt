package com.nbcteam5.nbccontact.data

import com.nbcteam5.nbccontact.R

object ContactDatabase {

    //data
    private val contact1 = ContactData(1,"서준","","01011111111","경기도","boll1235@gmail.com",false)
    private val contact2 = ContactData(2,"지현", "","01011111112","경기도","boll@gmail.com"false)
    private val contact3 = ContactData(3,"민지", "","01011111110","서울","boll12@gmail.com",false)
    private val contact4 = ContactData(4,"준호", "","01011111117","경기도","boll0@gmail.com",false)
    private val contact5 = ContactData(5,"윤지", "","01011111112","인천","bol@gmail.com",false)
    private val contact6 = ContactData(6,"혜진", "","01011211112","부산","boll555@gmail.com",false)
    private val contact7 = ContactData(7,"승현", "","01011111712","제주도","crab@gmail.com",false)
    private val contact8 = ContactData(8,"수진", "","01011111100","경상도","bo@gmail.com",false)
    private val contact9 = ContactData(9,"재원", "","010111111612","경기도","boll999@gmail.com",false)
    private val contact10 = ContactData(10,"애리", "","010112311612","전라도","bl999@gmail.com",false)
    private val contact11 = ContactData(11,"현우", "","010231111612","경상북도","boll999@gmail.com",false)


    val totalContactData:ArrayList<ContactData> = arrayListOf(contact1, contact2, contact3, contact4,contact5,
        contact6,
        contact7,
        contact8,contact9,contact10,contact11)


    //다른 필요 메서드 작성
}