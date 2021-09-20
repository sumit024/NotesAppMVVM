package com.app_devs.noteit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val title:String,
        val subtitle:String,
        val notes:String,
        val date:String,
        val priority:String
)
