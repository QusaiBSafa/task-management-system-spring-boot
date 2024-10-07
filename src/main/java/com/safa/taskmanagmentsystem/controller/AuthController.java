package com.safa.taskmanagmentsystem.controller;

import com.safa.taskmanagmentsystem.entitie.dtos.AuthRequest;
import com.safa.taskmanagmentsystem.entitie.dtos.AuthResponse;
import com.safa.taskmanagmentsystem.exception.BadRequestException;
import com.safa.taskmanagmentsystem.exception.ForbiddenRequestException;
import com.safa.taskmanagmentsystem.service.impl.AuthService;
import com.safa.taskmanagmentsystem.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest authRequest) {
        try{
            AuthResponse authResponse= authService.login(authRequest.getEmail(), authRequest.getPassword());
            return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Invalid Username and Password");
        }
    }

    @GetMapping("/token/refresh")
    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refresh_token = authorizationHeader.substring("Bearer ".length());
            try {
                AuthResponse authResponse= authService.refreshToken(refresh_token);
                return ResponseEntity.ok(authResponse);
            } catch (Exception e) {
                throw new ForbiddenRequestException(e.getMessage());
            }
        }else{
            throw new BadRequestException("Refresh token is missing");
        }
    }
}
