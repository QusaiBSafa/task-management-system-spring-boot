package com.safa.taskmanagmentsystem.service;

import com.safa.taskmanagmentsystem.entitie.Task;
import com.safa.taskmanagmentsystem.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends BaseService<Task, TaskRepository>{

    public TaskService(TaskRepository repository) {
        super(repository);
    }

    public List<Task> getTasksByProject(long projectId) {
        return repository.findByProjectId(projectId);
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public List<Task> getUserTasks(long userId) {
        return repository.findByUserId(userId);
    }

    public void deleteTask(long taskId) {
        repository.deleteById(taskId);
    }

    public Task updateTaskStatus(long taskId, String status) {
        Task task = repository.findById(taskId).orElseThrow();
        task.setStatus(status);
        return repository.save(task);
    }
}
