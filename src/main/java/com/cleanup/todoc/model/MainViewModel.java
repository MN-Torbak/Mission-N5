package com.cleanup.todoc.model;

import androidx.lifecycle.ViewModel;

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

    public void getProject(long projectId, MainActivity.GetProjectListener getProjectListener) {
        executor.execute(() -> {
            Project project = projectDataSource.getProject(projectId);
            getProjectListener.onProjectRetrieved(project);
        });
    }

    public void getAllProject(MainActivity.GetAllProjectListener getAllProjectListener) {
        executor.execute(() -> {
            getAllProjectListener.onAllProjectRetrieved(projectDataSource.getAllProjects());
        });
    }

    // -------------
    // FOR TASK
    // -------------

    public void getAllTasks(MainActivity.GetAllTasksListener getAllTasksListener) {
        executor.execute(() -> {
            List<Task> tasks = taskDataSource.getAllTasks();
            getAllTasksListener.onTasksRetrieved(tasks);
        });
    }

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
}