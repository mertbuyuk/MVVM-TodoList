package com.mb.todolistmvvm.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "task")
@Parcelize
data class Task(
    val task : String,
    val important : Boolean = false,
    val check : Boolean = false,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id : Int = 0
) : Parcelable{
    val createdDateFormat : String
        get() = DateFormat.getDateInstance().format(created)
}