package com.security.spring_security.helper;

import com.security.spring_security.entities.UserInfo;
import com.security.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JavaExpressUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           UserInfo userInfo = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User Not Found With username :"+username));
        return new CustomerUserDetails(userInfo);
    }
}
