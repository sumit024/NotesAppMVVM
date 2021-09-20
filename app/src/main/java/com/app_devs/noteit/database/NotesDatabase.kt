package com.app_devs.noteit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app_devs.noteit.dao.NotesDAO
import com.app_devs.noteit.model.Notes

@Database(entities = [Notes::class],version = 1)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun getDao():NotesDAO
    companion object{
        @Volatile
        var INSTANCE: NotesDatabase?=null
        fun getDatabaseInstance(context: Context): NotesDatabase
        {
            val temp= INSTANCE
            if(temp!=null)
            {
                return temp;
            }
            synchronized(this){
                return Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java,"notes_table").build()
            }

        }
    }
}