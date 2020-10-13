package com.cleanup.todoc;

import com.cleanup.todoc.model.Project;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProjectUnitTest {

    @Test
    public void test_get_project_from_project_id() {
        final Project project1 = new Project(1L, "Project 1", 1);
        final Project project2 = new Project(2L, "Project 2", 2);
        final Project project3 = new Project(3L, "Project 3", 3);
        assertEquals(1L, project1.getId());
        assertEquals(2L, project2.getId());
        assertEquals(3L, project3.getId());
    }

    @Test
    public void test_get_project_from_name() {
        final Project project1 = new Project(1L, "Project 1", 1);
        final Project project2 = new Project(2L, "Project 2", 2);
        final Project project3 = new Project(3L, "Project 3", 3);
        assertEquals("Project 1", project1.getName());
        assertEquals("Project 2", project2.getName());
        assertEquals("Project 3", project3.getName());
    }

    @Test
    public void test_get_project_from_color() {
        final Project project1 = new Project(1L, "Project 1", 1);
        final Project project2 = new Project(2L, "Project 2", 2);
        final Project project3 = new Project(3L, "Project 3", 3);
        assertEquals(1, project1.getColor());
        assertEquals(2, project2.getColor());
        assertEquals(3, project3.getColor());
    }
}
