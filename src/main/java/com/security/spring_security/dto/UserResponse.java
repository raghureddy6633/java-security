package com.security.spring_security.dto;

import com.security.spring_security.entities.UserRole;

import java.util.Set;

public class UserResponse {

    private Long id;
    private String username;
    private Set<UserRole> roles;

    public UserResponse(Long id, String username, Set<UserRole> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public UserResponse(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
