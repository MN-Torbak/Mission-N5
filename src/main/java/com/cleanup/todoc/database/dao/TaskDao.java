package com.cleanup.todoc.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    List<Task> getTasks(long projectId);

    @Query("SELECT * FROM Task")
    List<Task> getAllTasks();

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("DELETE FROM Task WHERE id = :taskId")
    void deleteTask(long taskId);
}