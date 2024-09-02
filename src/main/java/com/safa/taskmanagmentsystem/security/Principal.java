package com.safa.taskmanagmentsystem.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Principal {
    private Long id;
    private String username;
    private String email;
    private String password;

    public Principal(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Principal essence() {
        return new Principal(this.id, this.username, this.email);
    }
}
