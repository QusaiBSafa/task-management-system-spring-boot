package com.safa.taskmanagmentsystem.service.impl;

import com.safa.taskmanagmentsystem.entitie.BaseEntity;
import com.safa.taskmanagmentsystem.exception.BadRequestException;
import com.safa.taskmanagmentsystem.service.IBaseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class BaseService<T extends BaseEntity, ID, R extends JpaRepository<T, ID>> implements IBaseService<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
    protected final R repository;

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public T save(T entity) {
        try{
            LOGGER.debug("Saving {} to database", entity.getClass().getSimpleName());
            return repository.save(entity);
        } catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Transactional
    public T update(ID id, T entity) {
        if (id == null) {
            throw new BadRequestException("ID must not be null");
        }
        repository.findById(id).orElseThrow(()-> new BadRequestException("No record found with ID " + id));
        LOGGER.debug("Updating {} to database", entity.getClass().getSimpleName());
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public T findById(ID id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.orElse(null);
    }

    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }

}
