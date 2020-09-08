package com.cleanup.todoc.repositories;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    // --- GET Project ---
    public Project getProject(long projectId) { return this.projectDao.getProject(projectId); }

    // --- GET ALL Projects ---
    public List<Project> getAllProjects() { return this.projectDao.getAllProjects(); }
}