package com.safa.taskmanagmentsystem.exception;

import java.time.LocalDateTime;

public class BaseErrorModel {
    private LocalDateTime timestamp = LocalDateTime.now();
    private Integer status;
    private String error;
    private String path;

    public BaseErrorModel(Integer status, String error, String path) {
        this.status = status;
        this.error = error;
        this.path = path;
    }
}