package com.security.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v2")
@RestController
public class HelloController {


    @GetMapping("/normal")
    public String normalUserGreatings(){
        return "Hi! I am Normal User";
    }

    @GetMapping("/admin")
    public String adminlUserGreatings(){
        return "Hi! I,M Admin User";
    }
    @GetMapping("/guest")
    public String guestUserGreatings(){
        return "Hi! I am guest User";
    }
}
