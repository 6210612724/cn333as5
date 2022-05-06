package com.example.contact

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.app.NavUtils
import java.lang.Exception

class ContactDatabase(context:Context):SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION  = 1
        private const val DATABASE_NAME = "contact.db"
        private const val TBL_Contact = "tbl_contact"
        private const val ID = "id"
        private const val Firstname = "firstname"
        private const val Lastname= "lastname"
        private const val Numbertype = "numbertype"
        private const val Number = "number"


    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblContact = ("CREATE TABLE " + TBL_Contact+"("+
                                ID+" INTEGER PRIMARY KEY," + Firstname+" TEXT,"+ Lastname+" TEXT,"+
                                Numbertype + " TEXT,"+Number+" TEXT"+")")

        db?.execSQL(createTblContact)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int,newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_Contact")
        onCreate(db)
    }

    fun insertContact(contact: ContactModel):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,contact.id)
        contentValues.put(Firstname,contact.firstname)
        contentValues.put(Lastname,contact.lastname)
        contentValues.put(Numbertype,contact.numbertype)
        contentValues.put(Number,contact.number)

        val success = db.insert(TBL_Contact,null,contentValues)
        db.close()
        return success
    }


    @SuppressLint("Range")
    fun getAllcontact():ArrayList<ContactModel>{

        val contactList:ArrayList<ContactModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_Contact"
        val db = this.readableDatabase

        val cursor:Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }
        catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id:Int
        var firstname:String
        var lastname:String
        var numbertype:String
        var number:String

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                firstname = cursor.getString(cursor.getColumnIndex("firstname"))
                lastname = cursor.getString(cursor.getColumnIndex("lastname"))
                numbertype = cursor.getString(cursor.getColumnIndex("numbertype"))
                number = cursor.getString(cursor.getColumnIndex("number"))
                val contact =  ContactModel(id = id, firstname = firstname,lastname = lastname,numbertype=numbertype,number = number )

                contactList.add(contact)
            } while (cursor.moveToNext())
        }

        return contactList

    }

    fun updateContact(contact: ContactModel):Int{
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,contact.id)
        contentValues.put(Firstname,contact.firstname)
        contentValues.put(Lastname,contact.lastname)
        contentValues.put(Numbertype,contact.numbertype)
        contentValues.put(Number,contact.number)

        val success = db.update(TBL_Contact,contentValues, "id=" +contact.id,null)
        db.close()
        return success
    }

    fun deleteContact(id:Int):Int{
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        val success = db.delete(TBL_Contact, "id=$id",null)
        db.close()
        return success

    }



    }


