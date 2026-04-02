package com.security.spring_security.service;

import com.security.spring_security.dto.UserRequest;
import com.security.spring_security.dto.UserResponse;
import com.security.spring_security.entities.UserInfo;
import com.security.spring_security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();

    public UserResponse createuser(UserRequest userRequest) {
        // convertion of userrequest to userinfo
        UserInfo userInfo = modelMapper.map(userRequest, UserInfo.class);
        // convert the raw password into encoded password
        userInfo.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        // save the userinfo entity to the database
        return modelMapper.map(userRepository.save(userInfo), UserResponse.class);
    }
}
