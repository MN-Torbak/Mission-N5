package com.cleanup.todoc.repositories;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    // --- GET ---

    public List<Task> getTasks(long projectId){ return this.taskDao.getTasks(projectId); }

    public List<Task> getAllTasks() { return this.taskDao.getAllTasks(); }

    // --- CREATE ---

    public long createTask(Task task){ return taskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(long taskId){ taskDao.deleteTask(taskId); }

    // --- UPDATE ---
    public void updateTask(Task task){ taskDao.updateTask(task); }

}
