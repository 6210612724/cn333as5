package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class CreateContact : AppCompatActivity() {

    private lateinit var Firstname: EditText
    private lateinit var Lastaname: EditText
    private lateinit var Number: EditText
    private lateinit var SaveContact:Button
    private lateinit var numberType:RadioGroup
    private lateinit var contactDatabase:ContactDatabase





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)
        creatContactView()

        var selecttype:String =" "
        numberType.setOnCheckedChangeListener { group, checkedId ->
            selecttype = when(checkedId){
                R.id.molbile -> "molbile"
                R.id.home -> "home"
                R.id.work -> "work"
                else -> ""
            }
            Log.e("dd","$selecttype")
        }



        contactDatabase = ContactDatabase(this)
        SaveContact.setOnClickListener{ addContact(selecttype)}

    }

    private fun addContact(selecttype:String) {
        val firstname = Firstname.text.toString()
        val lastname = Lastaname.text.toString()
        val number = Number.text.toString()

        if (firstname.isEmpty() || lastname.isEmpty() ||  number.isEmpty() ){
            val toast = Toast.makeText(this@CreateContact, "Please Enter Require information", Toast.LENGTH_SHORT)
            toast.show()
        }else{


            val contact = ContactModel(firstname = firstname,lastname = lastname,numbertype = selecttype,number = number)
            val status = contactDatabase.insertContact(contact)

            if (status > -1){
                Toast.makeText(this@CreateContact, "Add New Contact", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                Log.e("ppp","$contact")
                startActivity(intent)

            }
        }

    }

    private fun creatContactView(){
        Firstname = findViewById(R.id.Firstname)
        Lastaname = findViewById(R.id.Lastname)
        Number = findViewById(R.id.Number)
        SaveContact = findViewById(R.id.SaveContact)
        numberType = findViewById(R.id.numberType)



    }
}