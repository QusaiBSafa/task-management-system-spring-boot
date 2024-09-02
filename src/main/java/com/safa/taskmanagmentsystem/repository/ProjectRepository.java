package com.safa.taskmanagmentsystem.repository;

import com.safa.taskmanagmentsystem.entitie.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUserId(long userId);
}
