package com.app_devs.noteit.model

import android.os.Parcel
import android.os.Parcelable
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
):Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(title)
                parcel.writeString(subtitle)
                parcel.writeString(notes)
                parcel.writeString(date)
                parcel.writeString(priority)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Notes> {
                override fun createFromParcel(parcel: Parcel): Notes {
                        return Notes(parcel)
                }

                override fun newArray(size: Int): Array<Notes?> {
                        return arrayOfNulls(size)
                }
        }
}
