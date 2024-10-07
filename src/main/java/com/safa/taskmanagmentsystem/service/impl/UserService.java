package com.safa.taskmanagmentsystem.service.impl;

import com.safa.taskmanagmentsystem.entitie.User;
import com.safa.taskmanagmentsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service

public class UserService extends BaseService<User, Long, UserRepository> {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User create(User user) throws SQLException {
        LOGGER.debug("Saving new user {} to the database", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }

}
