package com.example.contact

import java.util.*

data class ContactModel(
    var id:Int = getAutoID(),
    var firstname:String = "",
    var lastname:String = "",
    var numbertype:String = "",
    var number:String = ""
) {
    companion object{
        fun getAutoID():Int{
        val random = Random()
        return random.nextInt(100)

    }

    }


}

