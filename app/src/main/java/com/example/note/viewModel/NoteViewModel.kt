package com.example.note.viewModel

import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts.Data
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.note.model.NoteModel

class NoteViewModel: ViewModel()  {
    val notes = mutableStateListOf<NoteModel>()
    val selectedIdNote = mutableStateOf<NoteModel?>(null)
    fun addNote(
        title: String,
        content: String,
        data: String
    ){
        val newNote = NoteModel(notes.size+1,title,content,data)
        notes.add(newNote)
    }
    fun selectedNote(noteId:Int){
        selectedIdNote.value=notes.find { it.id==noteId }
    }
    fun deletedNote(
        noteModel: NoteModel
    ){
        notes.remove(noteModel)
    }
}