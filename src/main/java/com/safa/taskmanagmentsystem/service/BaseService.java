package com.safa.taskmanagmentsystem.service;

import com.safa.taskmanagmentsystem.entitie.BaseEntity;
import com.safa.taskmanagmentsystem.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@AllArgsConstructor
public class BaseService <T extends BaseEntity, R extends CrudRepository<T, Long>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
    protected final R repository;

    @Transactional(readOnly = true)
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public T save(T entity) {
        LOGGER.debug("Saving {} to database", entity.getClass().getSimpleName());
        return repository.save(entity);
    }

    @Transactional
    public T update(T entity) {
        if (entity.getId() == null) {
            throw new BadRequestException("ID must not be null");
        }
        repository.findById(entity.getId()).orElseThrow(()-> new BadRequestException("No record found with ID " + entity.getId()));
        LOGGER.debug("Updating {} to database", entity.getClass().getSimpleName());
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public T findById(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
