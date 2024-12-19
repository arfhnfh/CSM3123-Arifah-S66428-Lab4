package com.example.roomdatabasedemoo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskName: String,
    val isCompleted: Boolean,
    @ColumnInfo(name = "user_id") val userId: Int // Matches the query field
)








