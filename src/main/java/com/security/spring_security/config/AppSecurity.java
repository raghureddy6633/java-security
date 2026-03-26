package com.security.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurity {

    @Bean
    UserDetailsService userDetailsService(){
        UserDetails adminUser = User.withUsername("Krishna")
                .password(passwordEncoder().encode("Krishna"))
                .roles("ADMIN").build();
        UserDetails normalUser = User.withUsername("Raghu")
                .password(passwordEncoder().encode("Raghu"))
                .roles("USER").build();
        return new InMemoryUserDetailsManager(adminUser,normalUser);

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(req -> req.
                        requestMatchers("/v2/admin").hasRole("ADMIN")
                        .requestMatchers( "/v2/normal").hasRole("USER")
                        .requestMatchers("/v2/guest").permitAll())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();

    }

}
