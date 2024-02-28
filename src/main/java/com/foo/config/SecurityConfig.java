package com.foo.config;

import com.foo.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
public class SecurityConfig {

    private final SecurityService securityService;

    public SecurityConfig(SecurityService securityService) {
        this.securityService = securityService;
    }

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
              .rememberMe()
              .tokenValiditySeconds(60000)
              .key("foo")
              .userDetailsService(securityService)
              .and().build();

    }

}