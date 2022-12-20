package ru.otus.homework.hw12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/book**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/new**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/edit**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/delete**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/book").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/delete").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/denied")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .rememberMe().key("TopSecretCodeString").tokenValiditySeconds(2592000);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
