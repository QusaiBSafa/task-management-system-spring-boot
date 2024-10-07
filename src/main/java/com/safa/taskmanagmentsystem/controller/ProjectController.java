package com.safa.taskmanagmentsystem.controller;

import com.safa.taskmanagmentsystem.entitie.Project;
import com.safa.taskmanagmentsystem.service.impl.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController extends BaseController<Project, Long, ProjectService> {

    public ProjectController(ProjectService projectService) {
        super(projectService);
    }

}
