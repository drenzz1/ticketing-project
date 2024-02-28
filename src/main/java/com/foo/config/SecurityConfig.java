package com.foo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return   http.authorizeRequests()
                .requestMatchers("/","/login","/fragments/**","/assets/**","/images/**").permitAll()
                .requestMatchers("/user/**").hasAuthority("Admin")
                .requestMatchers("/project/**").hasAuthority("Manager")
                .requestMatchers("/task/employee/**").hasAuthority("Employee")
                .requestMatchers("/task/**").hasAuthority("Manager")
                .anyRequest().authenticated()
                .and()
              //.httpBasic()
              .formLogin()
              .loginPage("/login")
              .defaultSuccessUrl("/welcome")
              .failureUrl("/login?error=true")
              .permitAll()
              .and()
              .logout()
              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
              .logoutSuccessUrl("/login")
              .and()
              .build();

    }

}