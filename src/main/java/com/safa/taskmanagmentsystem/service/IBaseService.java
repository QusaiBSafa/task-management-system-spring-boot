package com.safa.taskmanagmentsystem.service;

import java.util.List;

public interface IBaseService <T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T t);

    T update(ID id, T t);

    void delete(ID t);
}
