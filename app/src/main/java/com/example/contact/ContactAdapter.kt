package com.example.contact

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contactList:ArrayList<ContactModel> = ArrayList()
    private var onClickItem: ((ContactModel) -> Unit)? =null

    fun addItem(items:ArrayList<ContactModel>){
        this.contactList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback:(ContactModel)-> Unit){
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.contact_layout,parent,false)
    )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bindView(contact)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(contact)}
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ContactViewHolder(var view:View): RecyclerView.ViewHolder(view){
        private var firstname = view.findViewById<TextView>(R.id.showFirstname)

        init {

        }


        fun bindView(contact:ContactModel){
            var fullname = contact.firstname + "  "+ contact.lastname
            firstname.text = fullname


        }
    }
}