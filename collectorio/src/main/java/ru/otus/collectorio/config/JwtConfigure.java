package ru.otus.collectorio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.otus.collectorio.service.impl.JWTService;

public class JwtConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    final JWTService jwtService;

    public JwtConfigure(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(jwtService);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
