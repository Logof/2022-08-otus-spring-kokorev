package ru.otus.collectorio.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.otus.collectorio.security.jwt.JwtTokenAuthenticationFilter;
import ru.otus.collectorio.security.jwt.JwtTokenProvider;

@Slf4j
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtTokenProvider tokenProvider) throws Exception {
        return http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .antMatchers(
                                    "/api/auth/register",
                                    "/api/auth/login",
                                    "/swagger-ui-custom.html" ,
                                    "/swagger-ui.html",
                                    "/swagger-ui/**",
                                    "/v3/api-docs/**",
                                    "/webjars/**",
                                    "/swagger-ui/index.html",
                                    "/api-docs/**")
                        .permitAll()
                        .antMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
