package com.security.spring_security.controller;

import com.security.spring_security.dto.UserRequest;
import com.security.spring_security.dto.UserResponse;
import com.security.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponse createRegistration(@RequestBody UserRequest userRequest){
        System.out.println("Request for user registration : "+userRequest);
        return userService.createuser(userRequest);
    }
}
