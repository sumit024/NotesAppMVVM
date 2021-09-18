package com.app_devs.noteit

import androidx.lifecycle.LiveData
import androidx.room.*

interface NotesDAO {
    @Query("SELECT * FROM notes_table")
    fun getNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notes: Notes)

    @Query("DELETE FROM notes_table where id=:id")
    fun deleteNode(id:Int)

    @Update
    fun updateNote(notes: Notes)
}