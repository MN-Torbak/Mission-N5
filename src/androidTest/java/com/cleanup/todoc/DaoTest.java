package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    // FOR DATA
    private TodocDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    // DATA SET FOR TEST
    private static long PROJECT_ID_1 = 1L;
    private static long PROJECT_ID_2 = 2L;
    private static long PROJECT_ID_3 = 3L;
    private static Project PROJECT_ONE = new Project(PROJECT_ID_1, "Projet Tartampion", 1);
    private static Project PROJECT_TWO = new Project(PROJECT_ID_2, "Projet Lucidia", 2);
    private static Project PROJECT_THREE = new Project(PROJECT_ID_3, "Projet Circus", 3);
    private static Task TASK_ONE = new Task(1, PROJECT_ID_1, "Task 1", new Date().getTime());
    private static Task TASK_TWO = new Task(2, PROJECT_ID_1, "Task 2", new Date().getTime());
    private static Task TASK_THREE = new Task(3, PROJECT_ID_1, "Task 3", new Date().getTime());
    private static Task TASK_FOUR = new Task(4, PROJECT_ID_2, "Task 4", new Date().getTime());
    private static Task TASK_FIVE = new Task(5, PROJECT_ID_2, "Task 5", new Date().getTime());
    private static Task TASK_SIX = new Task(6, PROJECT_ID_3, "Task 6", new Date().getTime());


    @Test
    public void insertAndGetProject() {
        // BEFORE : Adding a new project
        this.database.projectDao().createProject(PROJECT_ONE);
        // TEST
        Project project = this.database.projectDao().getProject(PROJECT_ID_1);
        assertTrue(project.getName().equals(PROJECT_ONE.getName()) && project.getId() == PROJECT_ID_1);
    }

    @Test
    public void getAllProject() {
        // BEFORE : Adding three new projects
        this.database.projectDao().createProject(PROJECT_ONE);
        this.database.projectDao().createProject(PROJECT_TWO);
        this.database.projectDao().createProject(PROJECT_THREE);
        // TEST
        assertEquals(this.database.projectDao().getAllProjects().size(), 3);
    }

    @Test
    public void getAllTasks() {
        // BEFORE : Adding three new projects and six new tasks
        this.database.projectDao().createProject(PROJECT_ONE);
        this.database.projectDao().createProject(PROJECT_TWO);
        this.database.projectDao().createProject(PROJECT_THREE);
        this.database.taskDao().insertTask(TASK_ONE);
        this.database.taskDao().insertTask(TASK_TWO);
        this.database.taskDao().insertTask(TASK_THREE);
        this.database.taskDao().insertTask(TASK_FOUR);
        this.database.taskDao().insertTask(TASK_FIVE);
        this.database.taskDao().insertTask(TASK_SIX);
        // TEST
        List<Task> tasks = this.database.taskDao().getAllTasks();
        assertEquals(tasks.size(), 6);
    }

    @Test
    public void insertAndGetTasks() {
        // BEFORE : Adding a new task in the project PROJECT_ONE
        this.database.projectDao().createProject(PROJECT_ONE);
        this.database.taskDao().insertTask(TASK_ONE);
        // TEST
        List<Task> tasks = this.database.taskDao().getTasks(PROJECT_ID_1);
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void deleteTask() {
        // BEFORE : Adding a new project and three new tasks in this project ; and delete one of these tasks
        this.database.projectDao().createProject(PROJECT_ONE);
        this.database.taskDao().insertTask(TASK_ONE);
        this.database.taskDao().insertTask(TASK_TWO);
        this.database.taskDao().insertTask(TASK_THREE);
        this.database.taskDao().deleteTask(TASK_TWO.getId());
        // TEST
        List<Task> tasks = this.database.taskDao().getTasks(PROJECT_ID_1);
        Assert.assertEquals(tasks.size(), 2);
        Assert.assertTrue(tasks.contains(TASK_ONE));
        Assert.assertTrue(tasks.contains(TASK_THREE));
        Assert.assertFalse(tasks.contains(TASK_TWO));
    }
}