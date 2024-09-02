package com.safa.taskmanagmentsystem.entitie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Task entity for creating the tasks for specific project with the ability to assign the task for specific user
 * @author qusaisafa
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    private String description;

    private String status;

    private String priority;

    private LockModeType deadLine;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
