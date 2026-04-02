package com.security.spring_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AppSecurity {

    @Autowired
    private DataSource dataSource;

    /* customizing the UserDetailsManager instead of jdbcUserDetailsManager and InMemoryUserDetailsManager due
    to we dont have over table creation and roles management.
    if we want to create our own tables w need to create our own custom userDetailsService
    */
    @Bean
    UserDetailsService userDetailsService(){
    return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(obj->obj.disable()) // disabiling the security for post, put, delete request
                .authorizeHttpRequests(req ->
                        req
                        .requestMatchers("/v2/admin").hasRole("ADMIN")
                        .requestMatchers( "/v2/normal").hasRole("USER")
                        .requestMatchers("/v2/guest","/api/user/create").permitAll())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();

    }

}
