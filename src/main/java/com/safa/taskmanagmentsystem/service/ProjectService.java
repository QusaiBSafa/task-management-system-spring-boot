package com.safa.taskmanagmentsystem.service;

import com.safa.taskmanagmentsystem.entitie.Project;
import com.safa.taskmanagmentsystem.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends BaseService<Project, ProjectRepository> {
    public ProjectService(ProjectRepository repository) {
        super(repository);
    }
}
