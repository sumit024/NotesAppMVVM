package com.app_devs.noteit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app_devs.noteit.repository.NotesRepository
import com.app_devs.noteit.database.NotesDatabase
import com.app_devs.noteit.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val repository: NotesRepository
    init{
        val dao=NotesDatabase.getDatabaseInstance(application).getDao()
        repository= NotesRepository(dao)
    }

    fun addNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) { repository.insertNote(notes) }
    fun getAllNotes():LiveData<List<Notes>> =  repository.getAllNotes()
    fun deleteNote(id:Int)=viewModelScope.launch(Dispatchers.IO) { repository.deleteNote(id)}
    fun updateNote(notes: Notes)=viewModelScope.launch(Dispatchers.IO) { repository.updateNote(notes)}
    fun deleteAllNotes()=viewModelScope.launch(Dispatchers.IO) { repository.deleteAllNotes()}

    fun getHighNotes():LiveData<List<Notes>> =  repository.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> = repository.getMediumNotes()
    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()


}