package com.security.spring_security.dto;


import com.security.spring_security.entities.UserRole;

import java.util.Set;

public class UserRequest {

    private String username;
    private String password;
    private Set<UserRole> roles;

    public UserRequest(){
    }

    public UserRequest(String username, String password, Set<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
