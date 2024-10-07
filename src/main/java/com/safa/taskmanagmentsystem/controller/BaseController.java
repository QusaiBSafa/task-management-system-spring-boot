package com.safa.taskmanagmentsystem.controller;

import com.safa.taskmanagmentsystem.entitie.BaseEntity;
import com.safa.taskmanagmentsystem.exception.BadRequestException;
import com.safa.taskmanagmentsystem.service.IBaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
public abstract class BaseController <T extends BaseEntity, ID, S extends IBaseService<T, ID>>{

    protected final S service;

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) throws Exception{
        T savedEntity = service.save(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = service.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        T entity = service.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
