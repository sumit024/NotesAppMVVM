package com.app_devs.noteit.repository

import androidx.lifecycle.LiveData
import com.app_devs.noteit.dao.NotesDAO
import com.app_devs.noteit.model.Notes

class NotesRepository(private val dao:NotesDAO) {

    fun getAllNotes():LiveData<List<Notes>> = dao.getNotes()

    suspend fun insertNote(notes:Notes)=dao.insertNote(notes)

    suspend fun deleteNote(id:Int)=dao.deleteNote(id)

    suspend fun updateNote(notes: Notes)= dao.updateNote(notes)
    suspend fun deleteAllNotes()=dao.deleteAllNotes()

    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()


}