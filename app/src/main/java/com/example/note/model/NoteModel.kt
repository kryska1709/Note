package com.example.note.model

import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts.Data

data class NoteModel(
    val id:Int,
    val title:String,
    val content:String,
    val date: String
) {

}