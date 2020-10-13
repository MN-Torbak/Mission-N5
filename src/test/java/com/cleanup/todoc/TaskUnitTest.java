package com.cleanup.todoc;

import com.cleanup.todoc.model.Task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
public class TaskUnitTest {

    Task task1;
    Task task2;
    Task task3;

    @Test
    public void test_get_task_from_project_id() {
        createTask();
        final Task task4 = new Task(4, 4L, "task 4", new Date().getTime());

        assertEquals(1L, task1.getProjectId());
        assertEquals(2L, task2.getProjectId());
        assertEquals(3L, task3.getProjectId());
        assertNotEquals(3L, task4.getProjectId());
    }

    @Test
    public void test_get_task_from_id() {
        createTask();
        assertEquals(1, task1.getId());
        assertEquals(2, task2.getId());
        assertEquals(3, task3.getId());
    }

    @Test
    public void test_get_task_from_name() {
        createTask();
        assertEquals("aaa", task1.getName());
        assertEquals("zzz", task2.getName());
        assertEquals("hhh", task3.getName());
    }


    @Test
    public void test_az_comparator() {
        createTask();

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskAZComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task2);
    }

    @Test
    public void test_za_comparator() {
        createTask();

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskZAComparator());

        assertSame(tasks.get(0), task2);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {
        createTask();

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskRecentComparator());

        assertSame(tasks.get(0), task3);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_old_comparator() {
        createTask();

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskOldComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }

    @Test
    public void test_project_comparator() {
        createTask();

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskProjectComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }

    public void createTask() {
        task1 = new Task(1, 1L, "aaa", 123);
        task2 = new Task(2, 2L, "zzz", 124);
        task3 = new Task(3, 3L, "hhh", 125);
    }

}