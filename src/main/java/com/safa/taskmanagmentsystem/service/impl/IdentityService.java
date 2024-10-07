package com.safa.taskmanagmentsystem.service.impl;

import com.safa.taskmanagmentsystem.entitie.User;
import com.safa.taskmanagmentsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IdentityService implements UserDetailsService {

    private final Logger LOGGER = LogManager.getLogger(IdentityService.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            LOGGER.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else if (!user.getActive()) {
            LOGGER.error("Failed to authenticate since user account is inactive");
            throw new UsernameNotFoundException("User is inactive");
        }
        return new User(user);
    }

}
