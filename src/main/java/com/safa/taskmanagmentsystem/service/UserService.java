package com.safa.taskmanagmentsystem.service;

import com.safa.taskmanagmentsystem.entitie.User;
import com.safa.taskmanagmentsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}
