package com.safa.taskmanagmentsystem.service.impl;

import com.safa.taskmanagmentsystem.entitie.Project;
import com.safa.taskmanagmentsystem.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends BaseService<Project, Long, ProjectRepository> {

    public ProjectService(ProjectRepository repository) {
        super(repository);
    }
}
