package com.security.spring_security.controller;

import com.security.spring_security.dto.AuthRequest;
import com.security.spring_security.dto.AuthResponse;
import com.security.spring_security.dto.UserRequest;
import com.security.spring_security.dto.UserResponse;
import com.security.spring_security.service.JwtService;
import com.security.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public UserResponse createRegistration(@RequestBody UserRequest userRequest) {
        System.out.println("Request for user registration : " + userRequest);
        return userService.createuser(userRequest);
    }

    @PostMapping("/authentication")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            AuthResponse response = new AuthResponse();
            response.setAccessToken(jwtService.generateJwtToken(request.getUsername()));
            return response;
        } else {
            throw new UsernameNotFoundException("User Not Found With Username :" + request.getUsername());
        }

    }
}
