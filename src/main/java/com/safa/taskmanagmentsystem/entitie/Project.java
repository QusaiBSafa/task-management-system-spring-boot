package com.safa.taskmanagmentsystem.entitie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Project entity for defining the project description and tasks
 * @author qusaisafa
 */
@Entity
@Getter
@Setter
public class Project extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
