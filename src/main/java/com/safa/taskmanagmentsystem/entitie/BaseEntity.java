package com.safa.taskmanagmentsystem.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.safa.taskmanagmentsystem.security.Principal;
import com.safa.taskmanagmentsystem.util.SecurityHelper;
import com.safa.taskmanagmentsystem.util.Utility;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @CreatedBy
    @Column(name = "creator_id")
    private String creatorId;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "editor_id")
    private String editorId;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created")
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "edited")
    private LocalDateTime editedAt;

    @JsonIgnore
    @Column(name = "creator")
    protected String creator;

    @JsonIgnore
    @Column(name = "editor")
    protected String editor;

    @Column(name = "storageMap", columnDefinition = "TEXT")
    protected String storageMap;

    @Column(name = "active")
    private Boolean active = true;


    @PrePersist
    public void onSave() {
        Principal principal = SecurityHelper.getPrincipal();
        if (principal != null) {
            this.creator = Utility.gson.toJson(principal.essence());
            this.creatorId = principal.getId().toString();
        }
        this.createdAt = Utility.now();
    }

    @PreUpdate
    public void onUpdate() {
        Principal principal = SecurityHelper.getPrincipal();
        if (principal != null) {
            this.editor = Utility.gson.toJson(principal.essence());
            this.editorId = principal.getId().toString();
        }
        this.editedAt = Utility.now();
    }
}