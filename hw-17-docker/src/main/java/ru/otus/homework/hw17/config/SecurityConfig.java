package ru.otus.homework.hw17.config;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.otus.homework.hw17.security.CustomAccessDeniedHandler;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder)
            .and()
            .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/book**").hasAnyRole("READER", "EDITOR")
                .antMatchers(HttpMethod.GET, "/new**").hasRole("EDITOR")
                .antMatchers(HttpMethod.GET, "/edit**").hasRole("EDITOR")
                .antMatchers(HttpMethod.GET, "/delete**").hasRole("EDITOR")
                .antMatchers(HttpMethod.POST, "/book**").hasRole("EDITOR")
                .antMatchers(HttpMethod.POST, "/delete**").hasRole("EDITOR")
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .logout()
                .logoutUrl("/logout");
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
