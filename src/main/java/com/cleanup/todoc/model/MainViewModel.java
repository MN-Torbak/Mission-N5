package com.cleanup.todoc.model;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.MainActivity;

import java.util.List;
import java.util.concurrent.Executor;

public class MainViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    // DATA
    public MainViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    // -------------
    // FOR PROJECT
    // -------------

    public Project getProject(long projectId) { return this.projectDataSource.getProject(projectId);  }

    public List<Project> getAllProject() { return this.projectDataSource.getAllProjects(); }

    // -------------
    // FOR TASK
    // -------------

    public List<Task> getTasks(long projectId) {
        return taskDataSource.getTasks(projectId);
    }

    public List<Task> getAllTasks() { return taskDataSource.getAllTasks(); }

    public void createTask(Task task, MainActivity.UpdateTaskListener updateTaskListener) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
            updateTaskListener.onUpdateTask();
        });
    }

    public void deleteTask(long projectId, MainActivity.UpdateTaskListener updateTaskListener) {
        executor.execute(() -> {
            taskDataSource.deleteTask(projectId);
            updateTaskListener.onUpdateTask();
        });
    }

    public void updateTask(Task task) {
        executor.execute(() -> {
            taskDataSource.updateTask(task);
        });
    }
}