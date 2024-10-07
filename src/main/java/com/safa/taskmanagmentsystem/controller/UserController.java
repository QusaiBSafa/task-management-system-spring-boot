package com.safa.taskmanagmentsystem.controller;

import com.safa.taskmanagmentsystem.entitie.User;
import com.safa.taskmanagmentsystem.service.impl.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Long, UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User user) throws Exception {
        User newUser = service.create(user);
        return ResponseEntity.ok(newUser);
    }
}
