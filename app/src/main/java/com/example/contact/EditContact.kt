package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class EditContact : AppCompatActivity() {

    private lateinit var editFirstname: EditText
    private lateinit var editLastaname: EditText
    private lateinit var editNumber: EditText
    private lateinit var editSave: Button
    private lateinit var numberType: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var contactDatabase:ContactDatabase




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        iniEdit()
        contactDatabase = ContactDatabase(this)
        var outputID:String = intent.getStringExtra("id").toString()
        var id:Int = outputID.toInt()
        var outputFirstname:String = intent.getStringExtra("firstname").toString()
        var outputLastname:String = intent.getStringExtra("lastname").toString()
        var outputNumbertype:String = intent.getStringExtra("numbertype").toString()
        var outputNumber:String = intent.getStringExtra("number").toString()

        editFirstname.setText(outputFirstname)
        editLastaname.setText(outputLastname)
        editNumber.setText(outputNumber)

        if (outputNumbertype == "moblie")
        {
            numberType.check(R.id.molbile)
        }

        if (outputNumbertype == "home"){
           numberType.check(R.id.home)
        }

        if (outputNumbertype == "work"){
            numberType.check(R.id.work)
        }

        numberType.setOnCheckedChangeListener { group, checkedId ->
            outputNumbertype = when(checkedId){
                R.id.molbile -> "molbile"
                R.id.home -> "home"
                R.id.work -> "work"
                else -> ""
            }
            Log.e("dd","$outputNumbertype")
        }


        val contact = ContactModel

        editSave.setOnClickListener {

            val firstname = editFirstname.text.toString()
            val lastname = editLastaname.text.toString()
            val number = editNumber.text.toString()


            val contact = ContactModel(id=id,firstname = firstname,lastname = lastname,numbertype = outputNumbertype,number = number )
            val status = contactDatabase.updateContact(contact)

            if (status > -1){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

        }


    }

    private fun iniEdit(){
        editFirstname = findViewById(R.id.editFirstname)
        editLastaname= findViewById(R.id.editLastname)
        editNumber = findViewById(R.id.editNumber)
        editSave = findViewById(R.id.editSave)
        numberType = findViewById(R.id.numberType)


    }
}