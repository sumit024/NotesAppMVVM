package com.app_devs.noteit

import androidx.lifecycle.LiveData
import com.app_devs.noteit.dao.NotesDAO
import com.app_devs.noteit.model.Notes

class NotesRepository(private val dao:NotesDAO) {

    fun getAllNotes():LiveData<List<Notes>> = dao.getNotes()

    suspend fun insertNote(notes:Notes)=dao.insertNote(notes)

    suspend fun deleteNote(id:Int)=dao.deleteNote(id)

    suspend fun updateNote(notes: Notes)= dao.updateNote(notes)

}