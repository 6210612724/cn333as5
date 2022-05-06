package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.reflect.typeOf

class ShowContact : AppCompatActivity() {

    private lateinit var fullname: TextView
    private lateinit var number: TextView
    private lateinit var editData:Button
    private lateinit var delete:Button
    private lateinit var contactDatabase:ContactDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact)
        contactDatabase = ContactDatabase(this)

        fullname = findViewById(R.id.fullname)
        number = findViewById(R.id.Number)
        editData = findViewById(R.id.editData)
        delete = findViewById(R.id.Delete)


        showData()



    }

    private fun showData(){
        var outputID:String = intent.getStringExtra("id").toString()
        var ID:Int = outputID.toInt()
        var outputFirstname:String = intent.getStringExtra("firstname").toString()
        val outputLastname:String = intent.getStringExtra("lastname").toString()
        val outputNumbertype:String = intent.getStringExtra("numbertype").toString()
        val outputNumber:String = intent.getStringExtra("number").toString()
        fullname.text =  outputFirstname+ "  " + outputLastname
        number.text = "("+outputNumbertype+ ") " +outputNumber

        editData.setOnClickListener {

            var firstname =  outputFirstname
            var lastname = outputLastname
            var number = outputNumber
            var numbertype = outputNumbertype
            var id = outputID


            val intent = Intent(this, EditContact::class.java)

            intent.putExtra("id", id)
            intent.putExtra("firstname", firstname)
            intent.putExtra("lastname", lastname)
            intent.putExtra("numbertype", numbertype)
            intent.putExtra("number", number)
            startActivity(intent)
        }

        delete.setOnClickListener {
            contactDatabase.deleteContact(ID)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }




    }



}