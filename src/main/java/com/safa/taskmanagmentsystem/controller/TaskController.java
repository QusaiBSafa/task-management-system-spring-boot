package com.safa.taskmanagmentsystem.controller;

import com.safa.taskmanagmentsystem.entitie.Task;
import com.safa.taskmanagmentsystem.service.impl.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController extends BaseController<Task, Long, TaskService> {

    public TaskController(TaskService service) {
        super(service);
    }
}
