package com.example.ToDoUgly.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ToDoUgly.entities.TaskLine

@Dao
interface TaskDAO {
    @Query("SELECT * FROM task_table")
    fun getAll(): List<TaskLine>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: TaskLine)

    @Delete
    suspend fun delete(task: TaskLine)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    @Query("UPDATE task_table SET new_task = :new_task")
    suspend fun update(new_task: String)
}