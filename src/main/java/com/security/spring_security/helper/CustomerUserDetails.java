package com.security.spring_security.helper;

import com.security.spring_security.entities.UserInfo;
import com.security.spring_security.entities.UserRole;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomerUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public CustomerUserDetails(UserInfo userInfo) {
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        // old style
        List<GrantedAuthority> auth = new ArrayList<>();
        for (UserRole role : userInfo.getRoles()){
            auth.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auth;
        // java 8 style
      //  authorities = userInfo.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getName().toUpperCase())).toList();

    }
    public CustomerUserDetails(){
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
