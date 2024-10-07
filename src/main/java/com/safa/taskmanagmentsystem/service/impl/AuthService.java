package com.safa.taskmanagmentsystem.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.safa.taskmanagmentsystem.entitie.User;
import com.safa.taskmanagmentsystem.entitie.dtos.AuthResponse;
import com.safa.taskmanagmentsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import java.util.Date;
import java.util.stream.Collectors;
import com.auth0.jwt.JWTVerifier;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //TODO
        return generateAuthToken(user, algorithm);
    }

    private AuthResponse generateAuthToken(User user, Algorithm algorithm) {
        String accessToken = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minutes
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withClaim("principal", user.toMap())
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 60 minutes
                .sign(algorithm);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(accessToken);
        authResponse.setRefreshToken(refreshToken);

        return authResponse;
    }

    public AuthResponse refreshToken(String refreshToken) {
         Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); // TODO
         JWTVerifier verifier = JWT.require(algorithm).build();
         DecodedJWT decodedJWT = verifier.verify(refreshToken);
         String email = decodedJWT.getSubject();
         User user = userRepository.findByEmail(email);

        return generateAuthToken(user, algorithm);
    }
}
