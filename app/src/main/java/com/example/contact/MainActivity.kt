package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var saveButton: FloatingActionButton
    private lateinit var contactDatabase:ContactDatabase
    private lateinit var recyclerView: RecyclerView
    private var adapter:ContactAdapter? = null
    private var contact:ContactModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contactDatabase = ContactDatabase(this)

        contactView()
        iniRecycleview()

        saveButton.setOnClickListener{
            val intent = Intent(this, CreateContact::class.java)
            startActivity(intent)
        }

        adapter?.setOnClickItem {
            var id = it.id.toString()
            var firstname = it.firstname
            var lastname = it.lastname
            var numbertype = it.numbertype
            var number = it.number
            contact = it

            val intent = Intent(this, ShowContact::class.java)
            intent.putExtra("id", id)
            intent.putExtra("firstname", firstname)
            intent.putExtra("lastname", lastname)
            intent.putExtra("numbertype", numbertype)
            intent.putExtra("number", number)
            startActivity(intent)


        }


    }

    private fun contactView(){
        recyclerView = findViewById(R.id.recycleView)
        saveButton = findViewById(R.id.SaveButton)


    }

    private fun iniRecycleview(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContactAdapter()
        recyclerView.adapter =  adapter
        val contactList = contactDatabase.getAllcontact()
        adapter?.addItem(contactList)

    }



}