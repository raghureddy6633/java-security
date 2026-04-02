package com.security.spring_security.config;

import com.security.spring_security.helper.JavaExpressUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class AppSecurity {



    /* customizing the UserDetailsManager instead of jdbcUserDetailsManager and InMemoryUserDetailsManager due
    to we dont have over table creation and roles management.
    if we want to create our own tables w need to create our own custom userDetailsService
    */
    @Bean
    UserDetailsService userDetailsService(){
    return new JavaExpressUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) {

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

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }

}
