package com.safa.taskmanagmentsystem.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", nullable = false)
    private String username;

    private String fullName;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @Size(min = 4, max = 255)
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    private String phone;

    @Column(name = "roles", nullable = false)
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;

    @Transient // Should not be persisted in the database.
    private Collection<String> roleCollection = new ArrayList<>();

    public User(User user) {
        this.setId(user.getId());
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.setActive(user.getActive());
        this.roles = user.getRoles();

        String rolesString = user.getRoles();
        if (rolesString != null && !rolesString.isEmpty()) {
            String[] roleNames = rolesString.split(",");
            for (String roleName : roleNames) {
                String trimmedRole = roleName.trim();
                if (!trimmedRole.isEmpty()) {
                    roleCollection.add(trimmedRole);
                }
            }
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String rolesString = getRoles();
        if (rolesString != null && !rolesString.isEmpty()) {
            String[] roleNames = rolesString.split(",");
            for (String roleName : roleNames) {
                String trimmedRole = roleName.trim();
                if (!trimmedRole.isEmpty()) {
                    authorities.add(new SimpleGrantedAuthority(trimmedRole));
                }
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.getActive();
    }

    public Map<String, Object> toMap() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", getId());
        map.put("username", this.username);
        map.put("email", this.email);
        return map;
    }

}
