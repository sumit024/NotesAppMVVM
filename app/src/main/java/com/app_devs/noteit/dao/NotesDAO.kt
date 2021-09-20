package com.app_devs.noteit.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app_devs.noteit.model.Notes
@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes_table")
    fun getNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)

    @Query("DELETE FROM notes_table where id=:id")
    suspend fun deleteNote(id:Int)

    @Update
    suspend fun updateNote(notes: Notes)
}