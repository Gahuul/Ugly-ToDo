package com.example.ToDoUgly.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskLine(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "new_task") val task: String?,

)
